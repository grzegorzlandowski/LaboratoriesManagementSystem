package pk.engineeringthesis.laboratoriesmanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard.InformationDashboard;
import pk.engineeringthesis.laboratoriesmanagementsystem.informationdashboard.InformationDashboardService;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.User;
import pk.engineeringthesis.laboratoriesmanagementsystem.users.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private InformationDashboardService informationdashboardservice;
    @Autowired
    private UserDetailsServiceImpl userservice;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
        List<InformationDashboard> informationdashboardlist = informationdashboardservice.findByIsActive(true);
        model.addAttribute("informationdashboardlist",informationdashboardlist);
        HttpSession session = request.getSession(true);
        Principal principal = request.getUserPrincipal();
        session.setAttribute("sessionusername", principal.getName());
        return "index";
    }
    @RequestMapping(value="/getcount", method= RequestMethod.GET)
    public @ResponseBody int getCount() {

        return userservice.userCountByStatus("Oczekuję na akceptacje");
    }




}
