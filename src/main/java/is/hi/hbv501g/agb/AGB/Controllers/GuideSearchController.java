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
 * 1     ars    021119  Added a search method for the search bar.
 */


import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class GuideSearchController {

    private GuideService guideService;

    @Autowired
    public GuideSearchController(GuideService guideService) { this.guideService = guideService; }


    //New guide object that the search method uses.
    @ModelAttribute("guide")
    public Guide defaultInstance() {
        Guide guide = new Guide();
        return guide;
    }

    /*Search that makes a list of guides depending on what the user searches for.
    Searches
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String searchGuide(@Valid @ModelAttribute(name = "guide") Guide guide,
                              BindingResult error, ModelMap model) {

        if(!error.hasErrors()) {
            ArrayList<Guide> guideList;
            guideList = (ArrayList<Guide>) guideService.findByMatches(guide);
            model.addAttribute("guideList", guideList);
        }

        return "searchresults";
    }


}
