package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard.InformationDashboard;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.LaboratoryService;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.UserDetailsServiceImpl;

import java.util.List;

@Controller
public class LaboratoryController {

    @Autowired
    UserDetailsServiceImpl userservice;
    @Autowired
    LaboratoryService laboratoryService;

    @RequestMapping("/dodajlaboratorium")
    public String addLaboratory(Model model){
        model.addAttribute("laboratory",new Laboratory());
        model.addAttribute("users",userservice.listAll());
        return "addlaboratory";
    }

    @RequestMapping("/zapiszlaboratorium")
    public String saveLaboratory(@ModelAttribute("laboratory") Laboratory laboratory, RedirectAttributes redirAttrs){


        try {
            laboratoryService.save(laboratory);
            redirAttrs.addFlashAttribute("status", "OK");
        }
        catch (Exception e )
        {
            redirAttrs.addFlashAttribute("status", "NOK");
        }

        return "redirect:/dodajlaboratorium";
    }

    @RequestMapping("/listalaboratoriow")
    public String laboratoryList(Model model) {
        List<Laboratory> laboratoryList = laboratoryService.listAll();
        model.addAttribute("laboratoryList",laboratoryList);
        return "laboratoryList";
    }


}
