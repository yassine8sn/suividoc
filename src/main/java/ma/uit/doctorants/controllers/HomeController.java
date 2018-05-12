package ma.uit.doctorants.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ma.uit.doctorants.models.Doctorant;
import ma.uit.doctorants.services.graphe.reponseGrapheService;


@Controller
public class HomeController {

	@Autowired 
	reponseGrapheService rg;
    @RequestMapping("/")
    public String home(Model model){
		model.addAttribute("addDoc",new Doctorant());
        return "redirect:/home";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        return new ModelAndView("login", "error", error);
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage(@RequestParam Optional<String> error) {
        return "home";
    }
   
}
