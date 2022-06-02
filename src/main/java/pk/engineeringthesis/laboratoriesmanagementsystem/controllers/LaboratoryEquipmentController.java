package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.Laboratory;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratory.LaboratoryService;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratoryequipment.EquipmentDetails;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratoryequipment.EquipmentDetailsService;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratoryequipment.LaboratoryEquipment;
import pk.engineeringthesis.laboratoriesmanagementsystem.laboratoryequipment.LaboratoryEquipmentService;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.UserDetailsServiceImpl;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class LaboratoryEquipmentController {

    @Autowired
    LaboratoryEquipmentService laboratoryEquipmentService;
    @Autowired
    EquipmentDetailsService equipmentDetailsService;
    @Autowired
    LaboratoryService laboratoryService;
    @Autowired
    UserDetailsServiceImpl userservice;


    @RequestMapping("/dodajwyposazenie/{id}")
    public String addLaboratoryEquipment(Model model, @PathVariable(name = "id") Long laboratoryid, HttpServletRequest request){

        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());
        Laboratory laboratory = laboratoryService.get(laboratoryid);
        if(laboratory.getSupervisorId()==user || user.getRole().equals("ROLE_ADMIN")) {
            model.addAttribute("laboratoryequipment", new LaboratoryEquipment());
            model.addAttribute("equipmentdetails", new EquipmentDetails());
            model.addAttribute("laboratoryid", laboratoryid);
            return "addlaboratoryequipment";
        }
        else
        {
            return "redirect:/403";


        }
    }

    @RequestMapping("/dodajwyposazeniezbazy/{id}")
    public String addLaboratoryEquipmentFromDatabase(Model model, @PathVariable(name = "id") Long laboratoryid, HttpServletRequest request){

        Principal principal = request.getUserPrincipal();
        User user =userservice.getUserByUsername(principal.getName());
        Laboratory laboratory = laboratoryService.get(laboratoryid);
        if(laboratory.getSupervisorId()==user || user.getRole().equals("ROLE_ADMIN")) {
            model.addAttribute("laboratoryequipment", new LaboratoryEquipment());
            model.addAttribute("equipmentdetails", equipmentDetailsService.listAll());
            model.addAttribute("laboratoryid", laboratoryid);
            return "addlaboratoryequipmentfromdatabase";
        }
        else
        {
            return "redirect:/403";

        }
    }

    @RequestMapping("/zapiszwyposazenie/{id}")
    public String saveLaboratoryEquipment(@ModelAttribute("laboratoryequipment") LaboratoryEquipment laboratoryEquipment,
                                          @ModelAttribute("equipmentdetails") EquipmentDetails equipmentDetails, @PathVariable(name = "id") Long laboratoryid,
                                          RedirectAttributes redirAttrs){

        equipmentDetails.setId(0);
        equipmentDetailsService.save(equipmentDetails);
        laboratoryEquipment.setId(0);
        laboratoryEquipment.setLaboratory(laboratoryService.get(laboratoryid));
        laboratoryEquipment.setEquipmentDetails(equipmentDetails);
        laboratoryEquipmentService.save(laboratoryEquipment);
        redirAttrs.addFlashAttribute("succes","OK");
        return "redirect:/laboratorium/"+laboratoryid;
    }
    @RequestMapping("/zapiszwyposazeniezbazy/{id}")
    public String saveLaboratoryEquipmentFromDatabase(@ModelAttribute("laboratoryequipment") LaboratoryEquipment laboratoryEquipment, @PathVariable(name = "id") Long laboratoryid,
                                          RedirectAttributes redirAttrs){

        // equipmentDetails.setId(0);
        //equipmentDetailsService.save(equipmentDetails);
        laboratoryEquipment.setId(0);
        laboratoryEquipment.setLaboratory(laboratoryService.get(laboratoryid));
        //laboratoryEquipment.setEquipmentDetails(equipmentDetails);
        laboratoryEquipmentService.save(laboratoryEquipment);
        redirAttrs.addFlashAttribute("succes","OK");
        return "redirect:/laboratorium/"+laboratoryid;
    }

    @RequestMapping("/usunstanowisko/{id}")
    public String deleteEquipment(Model model,@PathVariable(name = "id") Long id, RedirectAttributes redirAttrs){

        LaboratoryEquipment laboratoryEquipment=laboratoryEquipmentService.get(id);
        laboratoryEquipmentService.delete(id);
        redirAttrs.addFlashAttribute("succes","OK");
        return "redirect:/laboratorium/"+laboratoryEquipment.getLaboratory().getId();
    }

    @RequestMapping("/edytujwyposazenie/{id}")
    public String editLaboratory(Model model,@PathVariable(name = "id") Long id){

        LaboratoryEquipment laboratoryEquipment= laboratoryEquipmentService.get(id);
        EquipmentDetails equipmentDetails= laboratoryEquipment.getEquipmentDetails();
        model.addAttribute("editlaboratoryequipment",laboratoryEquipment);
        model.addAttribute("editequipmentdetails",equipmentDetails);
        return "editlaboratoryequipment";
    }

    @RequestMapping("/edytujwyposazenie/zapisz")
    public String saveEditedEquipment(@ModelAttribute("editlaboratoryequipment") LaboratoryEquipment laboratoryEquipment,
                                          @ModelAttribute("editequipmentdetails") EquipmentDetails equipmentDetails, RedirectAttributes redirAttrs){
        System.out.println(laboratoryEquipment.getEquipmentDetails().getId());
        equipmentDetails.setId(laboratoryEquipment.getEquipmentDetails().getId());
        equipmentDetailsService.save(equipmentDetails);
        laboratoryEquipmentService.save(laboratoryEquipment);
        redirAttrs.addFlashAttribute("succes","OK");
        return "redirect:/laboratorium/"+laboratoryEquipment.getLaboratory().getId();
    }

    @RequestMapping("/usunwyposazenie/{id}")
    public String deleteequipmentdetails(Model model,@PathVariable(name = "id") Long id, RedirectAttributes redirAttrs){



        LaboratoryEquipment laboratoryEquipment=laboratoryEquipmentService.get(id);
        EquipmentDetails equipmentDetails= laboratoryEquipment.getEquipmentDetails();
        Integer count = equipmentDetailsService.countEquipmentDetailst(equipmentDetails);
        if(count>1)
        {

            redirAttrs.addFlashAttribute("delete","NOK");

        }
        else
        {
            laboratoryEquipmentService.delete(id);
            equipmentDetailsService.delete(equipmentDetails.getId());
            redirAttrs.addFlashAttribute("succes","OK");

        }
        return "redirect:/laboratorium/"+laboratoryEquipment.getLaboratory().getId();

    }
    @RequestMapping("/wyposazenie/{id}")
    public String getLaboratoryEquipment(Model model, @PathVariable(name = "id") Long id) {

        model.addAttribute("laboratoryequipment",laboratoryEquipmentService.get(id));
        return "getlaboratoryequipment";
    }
}
