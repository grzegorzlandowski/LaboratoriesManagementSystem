package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import pk.engineeringthesis.laboratoriesmanagementsystem.notification.Notification;
import pk.engineeringthesis.laboratoriesmanagementsystem.notification.NotificationService;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.UserDetailsServiceImpl;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserDetailsServiceImpl userservice;


    @RequestMapping(value="/pokazostatniepowiadomienia", method = RequestMethod.GET)
    public @ResponseBody
    List<Notification> getNotifcationstoNavbar(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());
        List<Notification> list4 = notificationService.getactivenotificationbyuserlimit5(user.getId());

            return list4;


    }

    @RequestMapping("/pokazpowiadomienia")
    public String showNotifications(Model model,HttpServletRequest request){

        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());

        List<Notification> notificationslist = notificationService.getactivenotificationbyuser(user);
        model.addAttribute("notificationslist", notificationslist);
        return "viewallnotifications";

    }

    @RequestMapping("/powiadoemienie/oznaczjakoprzeczytane/{id}")
    public String confirmNotification(@PathVariable(name = "id") Long id){

        try {
            Notification notification = notificationService.get(id);
            notification.setRead(true);
            notificationService.save(notification);
            return "redirect:/pokazpowiadomienia";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }

    @RequestMapping("/pokazpowiadomienia/archiwalne")
    public String showArchivalNotifications(Model model,HttpServletRequest request){

        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());

        List<Notification> notificationslist = notificationService.getarchivalnotificationbyuser(user);
        model.addAttribute("archivalnotificationslist", notificationslist);
        return "viewallarchivalnotifications";

    }
    @RequestMapping("/powiadoemienie/oznaczjakonieprzeczytane/{id}")
    public String restoreNotification(@PathVariable(name = "id") Long id){

        try {
            Notification notification = notificationService.get(id);
            notification.setRead(false);
            notificationService.save(notification);
            return "redirect:/pokazpowiadomienia/archiwalne";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }



}
