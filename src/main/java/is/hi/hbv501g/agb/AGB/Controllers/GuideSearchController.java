package is.hi.hbv501g.agb.AGB.Controllers;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 */


import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GuideSearchController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String searchGuide(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Guide> guide = GuideService.findByTitle(search);
        model.addAttribute("guides", guide);
        return "Velkominn";
    }


}
