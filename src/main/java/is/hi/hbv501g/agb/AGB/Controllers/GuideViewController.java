package is.hi.hbv501g.agb.AGB.Controllers;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * ars  Andrea Rakel    ars59@hi.is
 * jgs  Jónas G.        jgs7@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    ars     031119  Viewing info about chosen guide.
 * 2    eok     221119  Updated singleGuideView method to redirect to new page called "guide"
 * 3    jgs     231119  Connecting guides to its reviews
 * 4    eok     231119  Added adventurer to singleGuideView. This is the adventurer that created the guide.
 * 5    eok     261119  Added comments and removed unused functions.
 */

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Controller that takes care of displaying single guides and detailed information about them.
 */
@Controller
public class GuideViewController {

    private GuideService guideService;

    @Autowired
    public GuideViewController(GuideService guideService) {
        this.guideService = guideService;
    }

    /**
     * View a chosen guide from search results, and a list of its reviews.
     * @param id
     * @param model
     * @param session
     * @return A page with detailed information about guide with the param id.
     */
    @RequestMapping(value = "/guide/{id}", method = RequestMethod.GET)
    public String singleGuideView(@PathVariable("id") long id, Model model, HttpSession session) {

        // Giving the view access to the session
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if (sessionAdventurer != null) {
            model.addAttribute("sessionAdventurer", sessionAdventurer);
        }
         // Find the guide and the reviews
         Guide guide = guideService.findById(id).orElseThrow(
                 ()-> new IllegalArgumentException("Invalid guide ID"));
         model.addAttribute("guide", guide);

         return "guide";

    }
}
