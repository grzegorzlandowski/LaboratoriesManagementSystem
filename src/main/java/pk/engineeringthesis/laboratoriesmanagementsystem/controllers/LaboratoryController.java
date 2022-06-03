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
        switch(search.getAttribute()){
            case "laboratoryname":
                laboratoryList = searchService.searchLaboratoryByName(search.getValue());
                break;
            case "laboratorydescription":
                laboratoryList = searchService.searchLaboratoryByDescription(search.getValue());
                break;
            case "laboratoryintended":
                laboratoryList = searchService.searchLaboratoryByIntended(search.getValue());
                break;
            case"equipmentlocation":
                laboratoryList = searchService.searchLaboratoryByEquipmentLocation(search.getValue());
                break;
            case "equipmenttitle":
                laboratoryList = searchService.searchLaboratoryByEquipmentName(search.getValue());
                break;
            case "equipmenttype":
                laboratoryList = searchService.searchLaboratoryByEquipmentType(search.getValue());
                break;
            case "equipmentdescription":
                laboratoryList = searchService.searchLaboratoryByEquipmentDescription(search.getValue());
                break;
        }

        model.addAttribute("laboratoryList",laboratoryList);
        model.addAttribute("searchEngine",search);
        return "laboratoryList";
    }

    @RequestMapping(value ="/mojelaboratoria",method = RequestMethod.GET)
    public String laboratoryListSupervisor(Model model,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User user = userservice.getUserByUsername(principal.getName());
        List<Laboratory> laboratoryList = laboratoryService.findBySupervisor(user);
        model.addAttribute("laboratoryList",laboratoryList);
        model.addAttribute("mylaboratory","yes");
        model.addAttribute("searchEngine",new SearchEngine());
        return "laboratoryList";
    }

    @RequestMapping(value ="/mojelaboratoria",method = RequestMethod.POST)
    public String laboratorysearchertoSupervisor(Model model,@ModelAttribute("search Engine") SearchEngine search,HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        User user = userservice.getUserByUsername(principal.getName());
        List<Laboratory> laboratoryList = new ArrayList<>();

        switch(search.getAttribute()) {
            case "laboratoryname":
                laboratoryList = searchService.searchLaboratoryByNameWithSupervisor(search.getValue(), user.getUsername());
                break;
            case "laboratorydescription":
                laboratoryList = searchService.searchLaboratoryByDescriptionWithSupervisor(search.getValue(), user.getUsername());
                break;
            case "laboratoryintended":
                laboratoryList = searchService.searchLaboratoryByIntendedWithSupervisor(search.getValue(), user.getUsername());
                break;
            case "equipmentlocation":
                laboratoryList = searchService.searchLaboratoryByEquipmentLocationWithSupervisor(search.getValue(), user.getUsername());
                break;
            case "equipmenttitle":
                laboratoryList = searchService.searchLaboratoryByEquipmentNameWithSupervisor(search.getValue(), user.getUsername());
                break;
            case "equipmenttype":
                laboratoryList = searchService.searchLaboratoryByEquipmentTypeWithSupervisor(search.getValue(), user.getUsername());
                break;
            case "equipmentdescription":
                laboratoryList = searchService.searchLaboratoryByEquipmentDescriptionWithSupervisor(search.getValue(), user.getUsername());
                break;
        }
        model.addAttribute("laboratoryList",laboratoryList);
        model.addAttribute("searchEngine",search);
        model.addAttribute("mylaboratory","yes");
        return "laboratoryList";
    }


    @RequestMapping("/laboratorium/{id}")
    public String getLaboratory(Model model, @PathVariable(name = "id") Long id, HttpServletRequest request) {


        try {
            Principal principal = request.getUserPrincipal();
            User user = userservice.getUserByUsername(principal.getName());
            Laboratory laboratory = laboratoryService.get(id);
            if (laboratory.getSupervisorId() == user || user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("editor", "yes");
            } else {
                model.addAttribute("editor", "no");
            }
            model.addAttribute("laboratory", laboratory);
            return "getlaboratory";
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }

    @RequestMapping("/edytujlaboratorium/{id}")
    public String editLaboratory(Model model,@PathVariable(name = "id") Long id,HttpServletRequest request){

        try {
            Principal principal = request.getUserPrincipal();
            User user = userservice.getUserByUsername(principal.getName());
            Laboratory laboratory = laboratoryService.get(id);
            if (laboratory.getSupervisorId() == user || user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("editlaboratory", laboratory);
                model.addAttribute("users", userservice.findSupervisor());
                return "editlaboratory";
            } else {
                return "redirect:/403";
            }
        }
        catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
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
