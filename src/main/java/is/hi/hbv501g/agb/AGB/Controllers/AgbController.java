package is.hi.hbv501g.agb.AGB.Controllers;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    eok     031119  Created adventurers list and beta sign in options.
 * 2    eok     031119  Added a simple guides.html page that shows all the guides in the system. "/" maps to it
 */

// TODO: Delete BETA functions


import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.AdventurerService;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AgbController {

    private AdventurerService adventurerService;
    private GuideService guideService;

    @Autowired
    public AgbController(AdventurerService adventurerService, GuideService guideService){
        this.adventurerService = adventurerService;
        this.guideService = guideService;
    }

    @RequestMapping(value = "/adventurers", method = RequestMethod.GET)
    public String adventurers(Model model){
        model.addAttribute("adventurers", adventurerService.findAll());
        return "adventurers";
    }

    @RequestMapping(value = "/betasignin", method = RequestMethod.POST)
    public String betaSignIn(HttpSession session) {

        // define params
        String testEmail = "test@test";
        String testDisplayName = "test";
        String testPassword = "test";

        // terminate current session if any
        session.setAttribute("SignedInAdventurer", null);

        Adventurer sessionAdventurer = new Adventurer();

        if (!adventurerService.findByEmail(testEmail).isPresent()) {
            // create tester if he does not exists
            System.out.println("creating tester");
            sessionAdventurer.setEmail(testEmail);
            sessionAdventurer.setDisplayName(testDisplayName);
            sessionAdventurer.setPasswordHashed(testPassword);
            adventurerService.signUp(sessionAdventurer);
        }
        else {
            sessionAdventurer = adventurerService.findByEmail(testEmail).get();
        }

         // sign tester in
         adventurerService.signIn(sessionAdventurer);
         session.setAttribute("SignedInAdventurer", sessionAdventurer);

         return "redirect:/profile";
    }


    @RequestMapping(value = "/guides", method = RequestMethod.GET)
    public String guides(Model model){
        model.addAttribute("guides", guideService.findAll());
        return "guides";
    }
}
