package is.hi.hbv501g.agb.AGB.Controllers;

import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }
}
