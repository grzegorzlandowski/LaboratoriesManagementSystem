package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pk.engineeringthesis.laboratoriesmanagementsystem.config.MailService;
import pk.engineeringthesis.laboratoriesmanagementsystem.config.PasswordEncryption;
import pk.engineeringthesis.laboratoriesmanagementsystem.notification.Notification;
import pk.engineeringthesis.laboratoriesmanagementsystem.notification.NotificationService;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.UserDetailsServiceImpl;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private MailService mailService;
    @Autowired
    private UserDetailsServiceImpl userservice;
    @Autowired
    private PasswordEncryption encryptpassword;
    @Autowired
    private NotificationService notificationService;

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
            model.addAttribute("wrongemail","Adres E-mail który wpisałeś jest zajęty! Użyj innego.");
            return "register";
        }
        else
        {
            String pass=user.getPassword();
            String codepass= encryptpassword.encodepassword(pass);
            user.setPassword(codepass);
            user.setRole("ROLE_EMPLOYEE");
            user.setEnabled(true);
            user.setStatus("Oczekuję na akceptacje");
            userservice.save(user);
            mailService.sendMail(user.getEmail(),"System Zarządzania Laboratoriami Wydziałowymi - Potwierdzenie Rejestracji",
                    "<center><h1>Rejestracja przebiegła pomyślnie!</h1></center>"+
                            "Twoje konto zostało założone, zanim będziesz mógł z niego korzystać poczekaj na zakcepotwanie przez administratora." +
                            "O aktywowaniu swojego konta zostaniesz również powiadomiony mailowo.",true);

            User admin= userservice.getUserByUsername("administrator");
            if(admin!= null) {
                Notification notification = new Notification();
                notification.setUser(admin);
                notification.setDate(LocalDateTime.now());
                notification.setMessage("Użytkownik " + user.getUsername() + " zarejestrował się. Potwierdź użytkownika.");
                notification.setRead(false);
                notificationService.save(notification);
            }
            return "registerconfirm";
        }
    }
    @RequestMapping("/potwierdzuzytkownika")
    public String confirmUserList(Model model){

        model.addAttribute("confirmuserlist",userservice.getUserByStatus("Oczekuję na akceptacje"));
        return "confirmuserlist";
    }

    @RequestMapping("/potwierdzuzytkownika/{id}")
    public String confirmUser(@PathVariable(name = "id") Long id)throws MessagingException{

        User user=userservice.getUserById(id);
        user.setStatus("Zaakceptowane");
        userservice.save(user);
        mailService.sendMail(user.getEmail(),"System Zarządzania Laboratoriami Wydziałowymi - Konto Potwierdzone!",
                "<center><h1>Administrator potwierdził twoje konto!</h1></center>"+
                        "Twoje konto zostało potwierdzone przez administratora. Teraz możesz się zalogować wykorzystującswoje dane(login oraz hasło) " +
                        "wpisane podczas rejestracji",true);
        return "redirect:/potwierdzuzytkownika";
    }

    @RequestMapping("/nowyuzytkownik")
    public String registerByAdmin(Model model){

        model.addAttribute("newuser",new User());
        return "registerbyadmin";
    }


    @RequestMapping(value="/nowyuzytkownik/zapiszuzytkownika", method=RequestMethod.POST)
    public String SaveUserByAdmin(Model model, @ModelAttribute("newuser") User user, RedirectAttributes redirAttrs)throws MessagingException{

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
            model.addAttribute("wrongemail","Adres E-mail który wpisałeś jest zajęty! Użyj innego.");
            return "register";
        }
        else
        {
            String pass=user.getPassword();
            String codepass= encryptpassword.encodepassword(pass);
            user.setPassword(codepass);
            user.setStatus("Zaakceptowane");
            userservice.save(user);
            mailService.sendMail(user.getEmail(),"System Zarządzania Laboratoriami Wydziałowymi - Potwierdzenie Stworzenia konta przez administratora",
                    "<center><h1>Adminstrator stworzył dla Ciebie konto!</h1></center>"+
                            "Twoje konto zostało stworzone przez administratora, teraz możesz się zalogować korzystając z loginu " + user.getUsername()+
                            ". Jeśli jeszcze nie masz hasła skontaktuj się z administratorem.",true);
           redirAttrs.addFlashAttribute("status", "OK");

            return "redirect:/nowyuzytkownik";
        }
    }

    @RequestMapping("/zarzadzanieuzytkownikami/lista")
    public String index(Model model) {
        List<User> userlist = userservice.findEmployeeAndSupervisor();
        model.addAttribute("userlist",userlist);
        return "usermanagementlist";
    }

    @RequestMapping("/edytujuzytkownika/{id}")
    public String editUserByAdmin(Model model,@PathVariable(name = "id") Long id){

        User user=userservice.getUserById(id);
       model.addAttribute("edituser",user);
        return "edituserbyadmin";
    }

    @RequestMapping(value="/edytujuzytkownika/zapiszuzytkownika", method=RequestMethod.POST)
    public String SaveUserByAdmin(Model model, @ModelAttribute("edituser") User user){


        User userExistsEmail = userservice.getUserByEmail(user.getEmail());
        User thisuser = userservice.getUserByUsername(user.getUsername());
        if(userExistsEmail!=null && thisuser!= userExistsEmail){
            model.addAttribute("edituser",user);
            model.addAttribute("wrongemail","Adres E-mail który wpisałeś jest zajęty! Użyj innego.");
            return "edituserbyadmin";
        }
        else
        {
            user.setPassword(thisuser.getPassword());
            user.setStatus("Zaakceptowane");
            userservice.save(user);

            return "redirect:/zarzadzanieuzytkownikami/lista";
        }
    }

    @RequestMapping("/edytujhaslouzytkownika/{id}")
    public String editUserPasswordByAdmin(Model model,@PathVariable(name = "id") Long id){

        User user=userservice.getUserById(id);
        user.setPassword("");
        model.addAttribute("edituserpassword",user);
        return "edituserpasswordbyadmin";
    }

    @RequestMapping(value="/edytujhaslouzytkownika/zapiszuzytkownika", method=RequestMethod.POST)
    public String SaveUserPasswordByAdmin(Model model, @ModelAttribute("edituserpassword") User user){

             User thisuser = userservice.getUserById(user.getId());
             String pass=user.getPassword();
             String codepass= encryptpassword.encodepassword(pass);
             thisuser.setPassword(codepass);
             userservice.save(thisuser);
            return "redirect:/zarzadzanieuzytkownikami/lista";

    }

    @RequestMapping("/zablokujuzytkownika/{id}")
    public String blockUser(Model model,@PathVariable(name = "id") Long id){

        User user=userservice.getUserById(id);
        user.setEnabled(false);
        userservice.save(user);
        return "redirect:/zarzadzanieuzytkownikami/lista";
    }

    @RequestMapping("/odblokujuzytkownika/{id}")
    public String unlockUser(Model model,@PathVariable(name = "id") Long id){

        User user=userservice.getUserById(id);
        user.setEnabled(true);
        userservice.save(user);
        return "redirect:/zarzadzanieuzytkownikami/lista";
    }
    @RequestMapping("/usunuzytkownika/{id}")
    public String deleteUser(Model model,@PathVariable(name = "id") Long id){

        userservice.delete(id);

        return "redirect:/zarzadzanieuzytkownikami/lista";
    }

    @RequestMapping("/edytujmojekonto")
    public String editMyUser(Model model, HttpServletRequest request){

        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());
        model.addAttribute("editmyuser",user);
        return "editmyaccount";
    }

    @RequestMapping(value="/edytujmojekonto/zapiszuzytkownika", method=RequestMethod.POST)
    public String SaveMyUser(Model model, @ModelAttribute("editmyuser") User user,RedirectAttributes redirAttrs){


        User userExistsEmail = userservice.getUserByEmail(user.getEmail());
        User thisuser = userservice.getUserByUsername(user.getUsername());
        if(userExistsEmail!=null && thisuser!= userExistsEmail){
            model.addAttribute("editmyuser",user);
            model.addAttribute("wrongemail","Adres E-mail który wpisałeś jest zajęty! Użyj innego.");
            return "editmyaccount";
        }
        else
        {
            user.setPassword(thisuser.getPassword());
            user.setStatus("Zaakceptowane");
            userservice.save(user);
            redirAttrs.addFlashAttribute("succes", "OK");
            return "redirect:/";
        }
    }

    @RequestMapping("/edytujmojehaslo")
    public String editMyUserPassword(Model model,HttpServletRequest request){

        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());
        user.setPassword("");
        model.addAttribute("editmyuserpassword",user);
        return "editmypassword";
    }

    @RequestMapping(value="/edytujmojehaslo/zapiszuzytkownika", method=RequestMethod.POST)
    public String SaveMyUserPassword( @ModelAttribute("editmyuserpassword") User user,RedirectAttributes redirAttrs){

        User thisuser = userservice.getUserById(user.getId());
        String pass=user.getPassword();
        String codepass= encryptpassword.encodepassword(pass);
        thisuser.setPassword(codepass);
        userservice.save(thisuser);
        redirAttrs.addFlashAttribute("succes", "OK");
        return "redirect:/";

    }


}
