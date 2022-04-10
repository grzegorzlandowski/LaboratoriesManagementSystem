package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard.InformationDashboard;
import pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard.InformationDashboardService;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private InformationDashboardService informationdashboardservice;

    @RequestMapping("/")
    public String index(Model model) {
        List<InformationDashboard> informationdashboardlist = informationdashboardservice.findByIsActive(true);

        model.addAttribute("informationdashboardlist",informationdashboardlist);
        return "index";
    }
    @RequestMapping("register")
    public String register() {
        return "register";
    }

}
