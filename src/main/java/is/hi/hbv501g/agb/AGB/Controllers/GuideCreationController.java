package is.hi.hbv501g.agb.AGB.Controllers;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * jgs  Jónas G.        jgs7@hi.is
 *
 * Changes:
 * no.  idProg  date    description
 * 1    jgs     031119  Making /createguide appear
 * 2    eok     031119  Added createGuide method and some stuff so that template can be displayed. Needs work.
 * 3    eok     261119  Added comments and removed unused functions.
 */


/**
 * Controller that takes care of creating (and deleting) guides.
 */
@Controller
public class GuideCreationController {

    private GuideService guideService;

    @Autowired
    public GuideCreationController(GuideService guideService){ this.guideService = guideService; }

    /**
     * @param guide
     * @param model
     * @param session
     * @return Guide creation Form for the platform.
     * Redirects to signIn if no one is signed in.
     */
    @RequestMapping(value = "/createguide", method = RequestMethod.GET)
    public String createGuideForm(Guide guide, Model model, HttpSession session) {
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if (sessionAdventurer == null) {
            model.addAttribute("error", "Must be signed in to create a guide.");
            return "redirect:/signin";
        } else {
            model.addAttribute("sessionAdventurer", sessionAdventurer);
            return "createguide";
        }
    }

    /**
     * Creates a guide based on the input
     * @param guide to be created
     * @param result
     * @param model
     * @param session
     * @return The single guide view for the newly created guide.
     * Redirects to signIn if no one is signed in.
     * Refreshes this page if input was incorrect.
     *
     */
    @RequestMapping(value = "/createguide", method = RequestMethod.POST)
    public String createGuide(@Valid Guide guide, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "createguide";
        }
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if (sessionAdventurer == null) {
            model.addAttribute("error", "Must be signed in to create a guide.");
            return "redirect:/signin";
        }

        try {
            // Save the new guide
            guideService.createGuide(guide, sessionAdventurer);

        } catch (DataIntegrityViolationException e) {
            // Check what constraint was violated, display error message.
            return "createguide";
        }

        return "redirect:/guide/" + guide.getId();
    }
}
