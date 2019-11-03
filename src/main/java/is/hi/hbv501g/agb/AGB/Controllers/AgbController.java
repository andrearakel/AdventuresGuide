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
 */


import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.AdventurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AgbController {

    private AdventurerService adventurerService;

    @Autowired
    public AgbController(AdventurerService adventurerService){ this.adventurerService = adventurerService; }

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


        if (adventurerService.findByEmail(testEmail).isPresent()) {
            // delete tester if exists
            System.out.println("deleting tester");
            adventurerService.delete(adventurerService.findByEmail(testEmail).get());
        }
         // create tester and sign him in
         System.out.println("creating tester");
         sessionAdventurer.setEmail(testEmail);
         sessionAdventurer.setDisplayName(testDisplayName);
         sessionAdventurer.setPasswordHashed(testPassword);
         adventurerService.signUp(sessionAdventurer);
         adventurerService.signIn(sessionAdventurer);
         session.setAttribute("SignedInAdventurer", sessionAdventurer);


        return "redirect:/profile";
    }

}
