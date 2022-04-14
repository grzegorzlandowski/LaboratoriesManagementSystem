package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pk.engineeringthesis.laboratoriesmanagementsystem.config.MailService;
import pk.engineeringthesis.laboratoriesmanagementsystem.config.PasswordEncryption;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.UserDetailsServiceImpl;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private MailService mailService;
    @Autowired
    private UserDetailsServiceImpl userservice;
    @Autowired
    private PasswordEncryption encryptpassword;

    @RequestMapping("/rejestracja")
    public String register(Model model){

        model.addAttribute("newuser",new User());
        return "register";
    }

    @RequestMapping(value="/rejestracja/zapiszuzytkownika", method=RequestMethod.POST)
    public String SaveUser(Model model,@ModelAttribute("newuser") User user)throws MessagingException{

        User userExists = userservice.getUserByUsername(user.getUsername());
        User userExistsEmail = userservice.getUserByEmail(user.getEmail());
        if(userExists != null)
        {
            model.addAttribute("newuser",user);
            model.addAttribute("wrongusername","Nazwa Którą wpisałeś jest zajęta! Użyj innej.");
            return "register";
        }
        else if(userExistsEmail!=null){
            model.addAttribute("newuser",user);
            model.addAttribute("wrongemail","Adres E-mail który wpisałeś jest zajęty! Użyj innej.");
            return "register";
        }
        else
        {
            String pass=user.getPassword();
            String codepass= encryptpassword.encodepassword(pass);
            user.setPassword(codepass);
            user.setRole("Pracownik");
            user.setEnabled(true);
            user.setStatus("Oczekuję na akceptacje");
            userservice.save(user);
            mailService.sendMail(user.getEmail(),"System Zarządzania Laboratoriami Wydziałowymi - Potwierdzenie Rejestracji",
                    "<center><h1>Rejestracja przebiegła pomyślnie!</h1></center>"+
                            "Twoje konto zostało założone, zanim będziesz mógł z niego korzystać poczekaj na zakcepotwanie przez administratora." +
                            "O aktywowaniu swojego konta zostaniesz również powiadomiony mailowo.",true);

            return "registerconfirm";
        }
    }
    @RequestMapping("/potwierdzuzytkownika")
    public String confirmUserList(Model model){

        model.addAttribute("confirmuserlist",userservice.getUserByStatus("Oczekuję na akceptacje"));
        return "confirmuserlist";
    }

    @RequestMapping("/potwierdzuzytkownika/{id}")
    public String confirmUser(@PathVariable(name = "id") Long id, HttpSession session){

        User user=userservice.getUserById(id);
        user.setStatus("Zaakceptowane");
        userservice.save(user);
        session.setAttribute("username", userservice.userCountByStatus("Oczekuję na akceptacje"));
        return "redirect:/potwierdzuzytkownika";
    }

}
