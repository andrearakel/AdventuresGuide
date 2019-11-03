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


import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class GuideViewController {

    @Autowired
    GuideService guideService;

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public String info(@RequestParam(value = "title", required = true) String title, ModelMap model) {
        ArrayList<Guide> chosenGuide;
        chosenGuide = (ArrayList<Guide>) guideService.findByTitle(title);
        model.addAttribute("chosenGuide", chosenGuide);
        return "informationPage";
    }
}
