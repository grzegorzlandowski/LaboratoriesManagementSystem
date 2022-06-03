package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard.InformationDashboard;
import pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard.InformationDashboardService;
import java.time.LocalDate;
import java.util.List;

@Controller
public class InformationDashboardController {

    @Autowired
    private InformationDashboardService informationdashboardservice;


    @RequestMapping("/aktualnosci/{id}")
    public String readInformationDashboard(@PathVariable(name = "id") Long id, Model model) {


        try{
            InformationDashboard informationDashboard = informationdashboardservice.get(id);
            model.addAttribute("readinformationdashboard",informationDashboard);
            return "readinformationdashboard";
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
            }


    }
    @RequestMapping("/dodajaktualnosc")
    public String addInformationDashboard(Model model){
        model.addAttribute("informationdashboard",new InformationDashboard());
        return "addinformationdashboard";
    }

    @RequestMapping("/zapiszaktualnosc")
    public String saveInformationDashboard(@ModelAttribute("informationdashboard") InformationDashboard informationdashboard, RedirectAttributes redirAttrs){

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


        try {
            InformationDashboard informationDashboard = informationdashboardservice.get(id);
            model.addAttribute("editinformationdashboard", informationDashboard);
            return "editinformationdashboard";
        }
        catch (Exception e)    {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
    }
    @RequestMapping("/zaktualizujaktualnosc/{id}")
    public String saveEditedItnformationDashboard(@ModelAttribute("editinformationdashboard") InformationDashboard informationdashboard,@PathVariable(name = "id") Long id) {

        informationdashboard.setId(id);
        informationdashboard.setIsactive(true);
        informationdashboard.setEditDate(LocalDate.now());
        try {
            informationdashboardservice.save(informationdashboard);

        }
        catch (Exception e )
        {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        return "redirect:/";
    }

    @RequestMapping("/archiwizuj/{id}")
    public String archiveInformationDashboard(@PathVariable(name = "id") Long id) {



        try {
            InformationDashboard informationDashboard = informationdashboardservice.get(id);
            informationDashboard.setIsactive(false);
            informationdashboardservice.save(informationDashboard);
        }
        catch (Exception e )
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
        return "redirect:/";
    }

    @RequestMapping("/archiwalneaktualnosci")
    public String archivalInformationDashboard(Model model) {
        List<InformationDashboard> informationdashboardlist = informationdashboardservice.findByIsActive(false);

        model.addAttribute("informationdashboardlist",informationdashboardlist);
        return "index";
    }
    @RequestMapping("/przywrocaktualnosc/{id}")
    public String restoreInformationDashboard(@PathVariable(name = "id") Long id) {


        try {
            InformationDashboard informationDashboard = informationdashboardservice.get(id);
            informationDashboard.setIsactive(true);
            informationdashboardservice.save(informationDashboard);

        }
        catch (Exception e )
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "object not found"
            );
        }
        return "redirect:/archiwalneaktualnosci";
    }
}
