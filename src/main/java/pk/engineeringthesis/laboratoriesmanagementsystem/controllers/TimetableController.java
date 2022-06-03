package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.LaboratoryService;
import pk.engineeringthesis.laboratoriesmanagementsystem.notification.Notification;
import pk.engineeringthesis.laboratoriesmanagementsystem.notification.NotificationService;
import pk.engineeringthesis.laboratoriesmanagementsystem.searchengine.SearchEngineService;
import pk.engineeringthesis.laboratoriesmanagementsystem.timetable.Timetable;
import pk.engineeringthesis.laboratoriesmanagementsystem.timetable.TimetableService;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.UserDetailsServiceImpl;
import pk.engineeringthesis.laboratoriesmanagementsystem.searchengine.SearchEngine;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TimetableController {

    @Autowired
    LaboratoryService laboratoryService;
    @Autowired
    UserDetailsServiceImpl userservice;
    @Autowired
    NotificationService notificationService;
    @Autowired
    TimetableService timetableService;
    @Autowired
    private SearchEngineService searchService;

    @RequestMapping("/nowytermin")
    public String NewReport(Model model){

        model.addAttribute("newtimetable",new Timetable());
        model.addAttribute("laboratorylist",laboratoryService.listAll());

        return "addnewtimetable";
    }

    @RequestMapping("/zapisztermin")
    public String SaveNewReport(@ModelAttribute("newtimetable") Timetable timetable, HttpServletRequest request, RedirectAttributes redirAttrs){

        List<Timetable> isfree =timetableService.isFree(timetable.getStart(),timetable.getEnd(),timetable.getLaboratory());
        if(!isfree.isEmpty())
        {
            redirAttrs.addFlashAttribute("notification", "Podany termin jest zajęty ");
            return "redirect:/nowytermin";
        }
        else if(timetable.getStart().isAfter(timetable.getEnd()))
        {
            redirAttrs.addFlashAttribute("notification", "Podana data rozpoczęcia jest późniejsza niż zakończenia. ");
            return "redirect:/nowytermin";
        }
        else if(timetable.getStart().isBefore(LocalDateTime.now()))
        {
            redirAttrs.addFlashAttribute("notification", "System nie umożliwia dodawanie terminów wstecz.");
            return "redirect:/nowytermin";
        }
        else {
            Laboratory laboratory = timetable.getLaboratory();
            User supervisor = laboratory.getSupervisorId();
            Principal principal = request.getUserPrincipal();
            User user = userservice.getUserByUsername(principal.getName());
            timetable.setUser(user);
            if (supervisor != user) {
                timetable.setConfirmed(false);
                Notification notification = new Notification();
                notification.setRead(false);
                notification.setUser(supervisor);
                notification.setDate(LocalDateTime.now());
                notification.setMessage("Dla sali " + laboratory.getName() + " powstał nowy termin. Czeka na potwierdzenie.");
                notificationService.save(notification);
            } else {
                timetable.setConfirmed(true);
            }
            timetableService.save(timetable);
            redirAttrs.addFlashAttribute("notificationok", "Termin dla sali "+timetable.getLaboratory().getName()+ " został dodany poprawnie. Rozpoczecie zajęć: "
                    +timetable.getStartFormatter()+" Zakończenie zajęć: "+timetable.getEndFormatter());
            return "redirect:/nowytermin";

        }
    }
    @RequestMapping("/kalendarz/{id}")
    public String viewCalendar(@PathVariable(name = "id") Long laboratoryid,Model model,HttpServletRequest request){

        try {
            Principal principal = request.getUserPrincipal();
            User user = userservice.getUserByUsername(principal.getName());
            Laboratory laboratory = laboratoryService.get(laboratoryid);
            if (laboratory.getSupervisorId() == user || user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("editor", "yes");
            } else {
                model.addAttribute("editor", "no");
            }
            model.addAttribute("laboratoryname", laboratory.getName());
            model.addAttribute("laboratoryid", laboratoryid);
            return "calendar";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }

    @RequestMapping(value="/kalendarz",method = RequestMethod.GET)
    public String laboratoryListToCalendar(Model model) {
        List<Laboratory> laboratoryList = laboratoryService.listAll();
        model.addAttribute("laboratoryList",laboratoryList);
        model.addAttribute("searchEngine",new SearchEngine());
        return "laboratorylisttocalendar";
    }
    @RequestMapping(value="/kalendarz",method = RequestMethod.POST)
    public String laboratoryListToCalendar(Model model,@ModelAttribute("searchEngine") SearchEngine searchEngine) {
        List<Laboratory> laboratoryList = searchService.searchLaboratoryByName(searchEngine.getValue());
        model.addAttribute("laboratoryList",laboratoryList);
        model.addAttribute("searchEngine",searchEngine);
        return "laboratorylisttocalendar";
    }

    @RequestMapping("/potwierdztermin")
    public String TimetabletoConfirm(Model model,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());
        List<Timetable> timetableList = timetableService.confirmTimetable(user);
        model.addAttribute("timetableList",timetableList);
        return "timetabletoconfirm";
    }

    @RequestMapping("/potwierdztermin/{id}")
    public String confirmEvent(@PathVariable(name = "id") Long timetableid){

        try {
            Timetable timetable = timetableService.get(timetableid);
            timetable.setConfirmed(true);
            timetableService.save(timetable);
            Notification notification = new Notification();
            notification.setUser(timetable.getUser());
            notification.setRead(false);
            notification.setDate(LocalDateTime.now());
            notification.setMessage("Twój termin dla Sali " + timetable.getLaboratory().getName() + " Rozpoczęcie: " + timetable.getStartFormatter() + " Zakończenie: " + timetable.getEndFormatter() +
                    " został potwierdzony przez opiekuna.");
            notificationService.save(notification);
            return "redirect:/potwierdztermin";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }
    @RequestMapping("/anulujtermin/{id}")
    public String deleteEvent(@PathVariable(name = "id") Long timetableid){

        try {
            Timetable timetable = timetableService.get(timetableid);
            timetableService.delete(timetableid);
            Notification notification = new Notification();
            notification.setUser(timetable.getUser());
            notification.setRead(false);
            notification.setDate(LocalDateTime.now());
            notification.setMessage("Twój termin dla Sali " + timetable.getLaboratory().getName() + " Rozpoczęcie: " + timetable.getStartFormatter() + " Zakończenie: " + timetable.getEndFormatter() +
                    " został anulowany przez opiekuna. W celu poznania przyczyny skontaktuj się z opiekunem.");
            notificationService.save(notification);

            return "redirect:/potwierdztermin";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }

}
