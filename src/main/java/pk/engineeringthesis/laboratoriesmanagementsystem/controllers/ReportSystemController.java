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
import pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem.ReportMessages;
import pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem.ReportMessagesService;
import pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem.ReportSystem;
import pk.engineeringthesis.laboratoriesmanagementsystem.reportsystem.ReportSystemService;
import pk.engineeringthesis.laboratoriesmanagementsystem.searchengine.SearchEngine;
import pk.engineeringthesis.laboratoriesmanagementsystem.searchengine.SearchEngineService;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.UserDetailsServiceImpl;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ReportSystemController {

    @Autowired
    ReportSystemService reportSystemService;
    @Autowired
    ReportMessagesService reportMessagesService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    LaboratoryService laboratoryService;
    @Autowired
    UserDetailsServiceImpl userservice;
    @Autowired
    private SearchEngineService searchService;

    @RequestMapping("/nowezgloszenie/{id}")
    public String NewReport(Model model,@PathVariable(name = "id") Long id){

        model.addAttribute("reportsystem",new ReportSystem());
        model.addAttribute("laboratoryid",id);

        return "addnewreport";
    }

    @RequestMapping("/zapiszzgloszenie/{id}")
    public String SaveNewReport(@ModelAttribute("reportsystem") ReportSystem reportSystem, @PathVariable(name = "id") Long laboratoryid,RedirectAttributes redirAttrs,HttpServletRequest request){

        try {
            reportSystem.setId(0);
            Laboratory laboratory = laboratoryService.get(laboratoryid);
            User supervisor = laboratory.getSupervisorId();
            Principal principal = request.getUserPrincipal();
            User applicant = userservice.getUserByUsername(principal.getName());
            reportSystem.setLaboratory(laboratory);
            if (supervisor != null) {
                reportSystem.setSupervisor(supervisor);
            }
            reportSystem.setApplicant(applicant);
            reportSystem.setDate(LocalDateTime.now());
            reportSystem.setStatus("Wprowadzone");
            Notification notification = new Notification();
            notification.setRead(false);
            notification.setUser(supervisor);
            notification.setDate(LocalDateTime.now());
            notification.setMessage("Dla sali " + laboratory.getName() + " powstało nowe zgłoszenie");

            reportSystemService.save(reportSystem);
            notificationService.save(notification);
            redirAttrs.addFlashAttribute("succes", "OK");
            return "redirect:/nowezgloszenie";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    @RequestMapping("/mojezgloszenia")
    public String ReportList(Model model,HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());

        model.addAttribute("reportSystemList",reportSystemService.getUserActiveReports(user));
        return "myreportlist";
    }

    @RequestMapping("/zgloszenie/{id}")
    public String getReport(Model model,@PathVariable(name = "id") Long id) {

        try {
            ReportSystem reportSystem = reportSystemService.get(id);
            model.addAttribute("reportSystem", reportSystem);
            model.addAttribute("reportMessage", reportMessagesService.getMessagetoReport(reportSystem));
            return "getreport";
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }

    @RequestMapping("/zgloszenie/{id}/nowawiadomosc")
    public String NewReportMessage(Model model,@PathVariable(name = "id") Long id){

        model.addAttribute("reportmessage",new ReportMessages());
        model.addAttribute("reportid",id);
        return "addnewreportmessage";
    }

    @RequestMapping("/zapiszwiadomosc/{id}")
    public String SaveNewReportMessage(@ModelAttribute("reportmessage") ReportMessages reportMessages, @PathVariable(name = "id") Long id,HttpServletRequest request){

        try {
            Principal principal = request.getUserPrincipal();
            User user = userservice.getUserByUsername(principal.getName());
            ReportSystem report = reportSystemService.get(id);
            reportMessages.setId(0);
            reportMessages.setReport(report);
            reportMessages.setDate(LocalDateTime.now());
            reportMessages.setUser(user);


            Notification notification = new Notification();
            notification.setRead(false);
            if (user == report.getSupervisor()) {
                notification.setUser(report.getApplicant());
            } else {
                notification.setUser(report.getSupervisor());
            }
            notification.setDate(LocalDateTime.now());
            notification.setMessage("Dla zgłoszenia o ID " + report.getId() + " użytkownik " + user.getUsername() + " dodał wiadomość");
            notificationService.save(notification);
            reportMessagesService.save(reportMessages);

            return "redirect:/zgloszenie/" + id;
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @RequestMapping("opiekun/mojezgloszenia")
    public String supervisorReportList(Model model,HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());

        model.addAttribute("reportSystemList",reportSystemService.getSupervisorActiveReports(user));
        return "supervisorreportlist";
    }

    @RequestMapping("/zgloszenie/{id}/zmienstatus")
    public String changeStatus(Model model,@PathVariable(name = "id") Long id){

        model.addAttribute("report",reportSystemService.get(id));
        model.addAttribute("reportid",id);
        return "changestatus";
    }

    @RequestMapping("/zmienstatuszgloszenia/{id}")
    public String saveChangeStatus(@ModelAttribute("report") ReportSystem reportSystem, @PathVariable(name = "id") Long id, RedirectAttributes redirAttrs){

        try {
            ReportSystem report = reportSystemService.get(id);
            report.setStatus(reportSystem.getStatus());
            reportSystemService.save(report);
            Notification notification = new Notification();
            notification.setRead(false);
            notification.setUser(report.getApplicant());
            notification.setDate(LocalDateTime.now());
            notification.setMessage("Dla zgłoszenia o ID " + report.getId() + " Zmieniono status na " + reportSystem.getStatus());
            redirAttrs.addFlashAttribute("succes", "OK");
            notificationService.save(notification);
            return "redirect:/zgloszenie/" + id;
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }

    @RequestMapping("opiekun/mojezgloszenia/archiwalne")
    public String supervisorArchivalReportList(Model model,HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());

        model.addAttribute("reportSystemList",reportSystemService.getSupervisorArchivalReports(user));
        return "supervisorreportarchivallist";
    }

    @RequestMapping("/mojezgloszenia/archiwalne")
    public String ReportArchivalList(Model model,HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());

        model.addAttribute("reportSystemList",reportSystemService.getUserArchivalReports(user));
        return "myreportarchivallist";
    }

    @RequestMapping(value = "/nowezgloszenie",method = RequestMethod.GET)
    public String laboratoryListToReportSystem(Model model) {
        List<Laboratory> laboratoryList = laboratoryService.listAll();
        model.addAttribute("laboratoryList",laboratoryList);
        model.addAttribute("searchEngine",new SearchEngine());
        return "laboratorylisttoreportsystem";
    }
    @RequestMapping(value = "/nowezgloszenie",method = RequestMethod.POST)
    public String laboratoryListToReportSystem(Model model,@ModelAttribute("searchEngine") SearchEngine searchEngine) {
        List<Laboratory> laboratoryList = searchService.searchLaboratoryByName(searchEngine.getValue());
        model.addAttribute("laboratoryList",laboratoryList);
        model.addAttribute("searchEngine",searchEngine);
        return "laboratorylisttoreportsystem";
    }

}
