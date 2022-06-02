package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pk.engineeringthesis.laboratoriesmanagementsystem.searchengine.SearchEngine;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.LaboratoryService;
import pk.engineeringthesis.laboratoriesmanagementsystem.searchengine.SearchEngineService;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LaboratoryController {

    @Autowired
    UserDetailsServiceImpl userservice;
    @Autowired
    LaboratoryService laboratoryService;

    @Autowired
    private SearchEngineService searchService;

    @RequestMapping("/dodajlaboratorium")
    public String addLaboratory(Model model){
        model.addAttribute("laboratory",new Laboratory());
        model.addAttribute("users",userservice.findSupervisor());
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

    @RequestMapping(value ="/listalaboratoriow",method = RequestMethod.GET)
    public String laboratoryList(Model model) {
        List<Laboratory> laboratoryList = laboratoryService.listAll();
        model.addAttribute("laboratoryList",laboratoryList);
        model.addAttribute("searchEngine",new SearchEngine());
        return "laboratoryList";
    }

    @RequestMapping(value ="/listalaboratoriow",method = RequestMethod.POST)
    public String laboratorysearcher(Model model,@ModelAttribute("search Engine") SearchEngine search) {

        List<Laboratory> laboratoryList = new ArrayList<>();

        if(search.getAttribute().equals("laboratoryname"))
        {
            laboratoryList = searchService.searchLaboratoryByName(search.getValue());
        }
        else if(search.getAttribute().equals("laboratorydescription"))
        {
            laboratoryList = searchService.searchLaboratoryByDescription(search.getValue());
        }
        else if(search.getAttribute().equals("laboratoryintended"))
        {
            laboratoryList = searchService.searchLaboratoryByIntended(search.getValue());
        }
        else if(search.getAttribute().equals("equipmentlocation"))
        {
            laboratoryList = searchService.searchLaboratoryByEquipmentLocation(search.getValue());
        }
        else if(search.getAttribute().equals("equipmenttitle"))
        {
            laboratoryList = searchService.searchLaboratoryByEquipmentName(search.getValue());
        }
        else if(search.getAttribute().equals("equipmenttype"))
        {
            laboratoryList = searchService.searchLaboratoryByEquipmentType(search.getValue());
        }
        else if(search.getAttribute().equals("equipmentdescription"))
        {
            laboratoryList = searchService.searchLaboratoryByEquipmentDescription(search.getValue());
        }

        model.addAttribute("laboratoryList",laboratoryList);
        model.addAttribute("searchEngine",search);
        return "laboratoryList";
    }

    @RequestMapping("/laboratorium/{id}")
    public String getReport(Model model, @PathVariable(name = "id") Long id, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());
        Laboratory laboratory = laboratoryService.get(id);
        if(laboratory.getSupervisorId()==user || user.getRole().equals("ROLE_ADMIN")){
            model.addAttribute("editor","yes");
        }
        else
        {
            model.addAttribute("editor","no");
        }
        model.addAttribute("laboratory",laboratory);
        return "getlaboratory";
    }

    @RequestMapping("/edytujlaboratorium/{id}")
    public String editLaboratory(Model model,@PathVariable(name = "id") Long id){
        model.addAttribute("editlaboratory",laboratoryService.get(id));
        model.addAttribute("users",userservice.findSupervisor());
        return "editlaboratory";
    }

    @RequestMapping("/zapiszedytowanelaboratorium")
    public String saveEditedLaboratory(@ModelAttribute("editlaboratory") Laboratory laboratory, RedirectAttributes redirAttrs){


        try {
            laboratoryService.save(laboratory);
            redirAttrs.addFlashAttribute("succes", "OK");
        }
        catch (Exception e )
        {
            redirAttrs.addFlashAttribute("succes", "NOK");
        }

        return "redirect:/laboratorium/"+laboratory.getId();
    }


}
