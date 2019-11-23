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
 * 1    ars     031119  Viewing info about chosen guide.
 * 2    eok     221119  Updated singleGuideView method to redirect to new page called "guide"
 * 3    eok     231119  Added adventurer to singleGuideView. This is the adventurer that created the guide.
 */

import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.AdventurerService;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GuideViewController {

    @Autowired
    GuideService guideService;
    AdventurerService adventurerService;

    //View a chosen guide from search results
    @RequestMapping(value = "/guide/{id}", method = RequestMethod.GET)
    public String singleGuideView(@PathVariable("id") long id,  Model model) {
        Guide guide = guideService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid guide ID"));
        model.addAttribute("guide", guide);

        /*
        // Get the adventurer that created this guide.
        // findById always seems to return null pointer exception for some reason
        Adventurer adventurer = adventurerService.
                findById(guide.getIdAdventurer()).orElseThrow(
                () -> new NullPointerException("Invalid adventurer ID"));
        model.addAttribute("adventurer", adventurer);
        */

        return "guide";
    }
}
