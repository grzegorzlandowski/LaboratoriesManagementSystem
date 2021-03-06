package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pk.engineeringthesis.laboratoriesmanagementsystem.config.MailService;
import pk.engineeringthesis.laboratoriesmanagementsystem.config.PasswordEncryption;
import pk.engineeringthesis.laboratoriesmanagementsystem.notification.Notification;
import pk.engineeringthesis.laboratoriesmanagementsystem.notification.NotificationService;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.UserDetailsServiceImpl;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
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
            model.addAttribute("wrongusername","Nazwa Kt??r?? wpisa??e?? jest zaj??ta! U??yj innej.");
            return "register";
        }
        else if(userExistsEmail!=null){
            model.addAttribute("newuser",user);
            model.addAttribute("wrongemail","Adres E-mail kt??ry wpisa??e?? jest zaj??ty! U??yj innego.");
            return "register";
        }
        else
        {
            String pass=user.getPassword();
            String codepass= encryptpassword.encodepassword(pass);
            user.setPassword(codepass);
            user.setRole("ROLE_EMPLOYEE");
            user.setEnabled(true);
            user.setStatus("Oczekuj?? na akceptacje");
            userservice.save(user);
            /*mailService.sendMail(user.getEmail(),"System Zarz??dzania Laboratoriami Wydzia??owymi - Potwierdzenie Rejestracji",
                    "<center><h1>Rejestracja przebieg??a pomy??lnie!</h1></center>"+
                            "Twoje konto zosta??o za??o??one, zanim b??dziesz m??g?? z niego korzysta?? poczekaj na zakcepotwanie przez administratora." +
                            "O aktywowaniu swojego konta zostaniesz r??wnie?? powiadomiony mailowo.",true); */

            User admin= userservice.getUserByUsername("administrator");
            if(admin!= null) {
                Notification notification = new Notification();
                notification.setUser(admin);
                notification.setDate(LocalDateTime.now());
                notification.setMessage("U??ytkownik " + user.getUsername() + " zarejestrowa?? si??. Potwierd?? u??ytkownika.");
                notification.setRead(false);
                notificationService.save(notification);
            }
            return "registerconfirm";
        }
    }
    @RequestMapping("/potwierdzuzytkownika")
    public String confirmUserList(Model model){

        model.addAttribute("confirmuserlist",userservice.getUserByStatus("Oczekuj?? na akceptacje"));
        return "confirmuserlist";
    }

    @RequestMapping("/potwierdzuzytkownika/{id}")
    public String confirmUser(@PathVariable(name = "id") Long id,RedirectAttributes redirAttrs)throws MessagingException{

        try {
            User user = userservice.getUserById(id);
            user.setStatus("Zaakceptowane");
            userservice.save(user);
            mailService.sendMail(user.getEmail(), "System Zarz??dzania Laboratoriami Wydzia??owymi - Konto Potwierdzone!",
                    "<center><h1>Administrator potwierdzi?? twoje konto!</h1></center>" +
                            "Twoje konto zosta??o potwierdzone przez administratora. Teraz mo??esz si?? zalogowa?? wykorzystuj??cswoje dane(login oraz has??o) " +
                            "wpisane podczas rejestracji", true);
            redirAttrs.addFlashAttribute("status", "OK");
            return "redirect:/potwierdzuzytkownika";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }

    @RequestMapping("/nowyuzytkownik")
    public String registerByAdmin(Model model){

        model.addAttribute("newuser",new User());
        return "registerbyadmin";
    }


    @RequestMapping(value="/nowyuzytkownik/zapiszuzytkownika", method=RequestMethod.POST)
    public String SaveUserByAdmin(Model model, @ModelAttribute("newuser") User user,RedirectAttributes redirAttrs)throws MessagingException{

        User userExists = userservice.getUserByUsername(user.getUsername());
        User userExistsEmail = userservice.getUserByEmail(user.getEmail());
        if(userExists != null)
        {
            model.addAttribute("newuser",user);
            model.addAttribute("wrongusername","Nazwa Kt??r?? wpisa??e?? jest zaj??ta! U??yj innej.");
            return "register";
        }
        else if(userExistsEmail!=null){
            model.addAttribute("newuser",user);
            model.addAttribute("wrongemail","Adres E-mail kt??ry wpisa??e?? jest zaj??ty! U??yj innego.");
            return "register";
        }
        else
        {
            String pass=user.getPassword();
            String codepass= encryptpassword.encodepassword(pass);
            user.setPassword(codepass);
            user.setStatus("Zaakceptowane");
            userservice.save(user);
            /*mailService.sendMail(user.getEmail(),"System Zarz??dzania Laboratoriami Wydzia??owymi - Potwierdzenie Stworzenia konta przez administratora",
                    "<center><h1>Adminstrator stworzy?? dla Ciebie konto!</h1></center>"+
                            "Twoje konto zosta??o stworzone przez administratora, teraz mo??esz si?? zalogowa?? korzystaj??c z loginu " + user.getUsername()+
                            ". Je??li jeszcze nie masz has??a skontaktuj si?? z administratorem.",true);*/
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

        try {
            User user = userservice.getUserById(id);
            user.getId();
            model.addAttribute("edituser", user);
            return "edituserbyadmin";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }

    @RequestMapping(value="/edytujuzytkownika/zapiszuzytkownika", method=RequestMethod.POST)
    public String SaveEditedUserByAdmin(Model model, @ModelAttribute("edituser") User user,RedirectAttributes redirAttrs){


        User userExistsEmail = userservice.getUserByEmail(user.getEmail());
        User thisuser = userservice.getUserByUsername(user.getUsername());
        if(userExistsEmail!=null && thisuser!= userExistsEmail){
            model.addAttribute("edituser",user);
            model.addAttribute("wrongemail","Adres E-mail kt??ry wpisa??e?? jest zaj??ty! U??yj innego.");
            return "edituserbyadmin";
        }
        else
        {
            user.setPassword(thisuser.getPassword());
            user.setStatus("Zaakceptowane");
            userservice.save(user);
            redirAttrs.addFlashAttribute("succes","OK");
            return "redirect:/zarzadzanieuzytkownikami/lista";
        }
    }

    @RequestMapping("/edytujhaslouzytkownika/{id}")
    public String editUserPasswordByAdmin(Model model,@PathVariable(name = "id") Long id){

        try {
            User user = userservice.getUserById(id);
            user.setPassword("");
            model.addAttribute("edituserpassword", user);
            return "edituserpasswordbyadmin";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }

    @RequestMapping(value="/edytujhaslouzytkownika/zapiszuzytkownika", method=RequestMethod.POST)
    public String SaveUserPasswordByAdmin( @ModelAttribute("edituserpassword") User user,RedirectAttributes redirAttrs){

             User thisuser = userservice.getUserById(user.getId());
             String pass=user.getPassword();
             String codepass= encryptpassword.encodepassword(pass);
             thisuser.setPassword(codepass);
             userservice.save(thisuser);
        redirAttrs.addFlashAttribute("succes","OK");
            return "redirect:/zarzadzanieuzytkownikami/lista";

    }

    @RequestMapping("/zablokujuzytkownika/{id}")
    public String blockUser(@PathVariable(name = "id") Long id){

        try {
            User user = userservice.getUserById(id);
            user.setEnabled(false);
            userservice.save(user);
            return "redirect:/zarzadzanieuzytkownikami/lista";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }

    @RequestMapping("/odblokujuzytkownika/{id}")
    public String unlockUser(@PathVariable(name = "id") Long id){

        try {
            User user = userservice.getUserById(id);
            user.setEnabled(true);
            userservice.save(user);
            return "redirect:/zarzadzanieuzytkownikami/lista";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }
    @RequestMapping("/usunuzytkownika/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id){

        try {
            userservice.delete(id);
            return "redirect:/zarzadzanieuzytkownikami/lista";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
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
            model.addAttribute("wrongemail","Adres E-mail kt??ry wpisa??e?? jest zaj??ty! U??yj innego.");
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
