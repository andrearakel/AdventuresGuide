package is.hi.hbv501g.agb.AGB.Controllers;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * ars  Andrea Rakel    ars59@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1.    ars    031119  Viewing info about chosen guide.
 */

import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class GuideViewController {

    @Autowired
    GuideService guideService;

    //View a chosen guide from search results
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public String info(@PathVariable("id") long id,  Model model) {
     Guide guide = guideService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid guide ID"));
     model.addAttribute("guides", guide);
     return "guides";
    }
}
