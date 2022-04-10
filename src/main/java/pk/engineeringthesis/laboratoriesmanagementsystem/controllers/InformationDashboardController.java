package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard.InformationDashboard;
import pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard.InformationDashboardService;
import java.time.LocalDate;

@Controller
public class InformationDashboardController {

    @Autowired
    private InformationDashboardService informationdashboardservice;


    @RequestMapping("/aktualnosci/{id}")
    public String readInformationDashboard(@PathVariable(name = "id") Long id, Model model) {

        model.addAttribute("readinformationdashboard",informationdashboardservice.get(id));
        return "readinformationdashboard";
    }
    @RequestMapping("/dodajaktualnosc")
    public String addInformationDashboard(Model model){
        model.addAttribute("informationdashboard",new InformationDashboard());
        return "addinformationdashboard";
    }

    @RequestMapping("/zapiszaktualnosc")
    public String saveInformationDashboard(@ModelAttribute("newProdukt") InformationDashboard informationdashboard, RedirectAttributes redirAttrs){

            informationdashboard.setIsactive(true);
            informationdashboard.setDate(LocalDate.now());
        try {
            informationdashboardservice.save(informationdashboard);
            redirAttrs.addFlashAttribute("status", "OK");
            }
       catch (Exception e )
            {
           redirAttrs.addFlashAttribute("status", "NOK");
            }

        return "redirect:/dodajaktualnosc";
    }

    @RequestMapping("/edytujaktualnosc/{id}")
    public String editnformationDashboard(@PathVariable(name = "id") Long id, Model model) {

        model.addAttribute("readinformationdashboard",informationdashboardservice.get(id));
        return "readinformationdashboard";
    }

}
