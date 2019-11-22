package is.hi.hbv501g.agb.AGB.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Programmers:
 * id   name            email
 * jgs  Jónas G.        jgs7@hi.is
 *
 * Changes:
 * no.  idProg  date    description
 * 1    jgs     041119  Made the controller so that "/" will appear
 *
 **/

@Controller
public class HomeController {
    private String appMode;

    @Autowired
    public HomeController(Environment environment){
        appMode = environment.getProperty("app-mode");
    }


    @RequestMapping(value = "/")
    public String home(Model model) {

        model.addAttribute("mode", appMode);

        return "home";
    }

}
