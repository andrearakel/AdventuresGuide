package is.hi.hbv501g.agb.AGB.Controllers;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.AdventurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * boj  Bjartur         boj8@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    boj     211019  Gerði grunn að sign in.
 * 2    eok     311019  Fixed typo. Added "redirect:" in front of return statement on successful signin. Created quick-fix for signIn after hashing password on signUp.
 * 3    boj     011119  Changed signIn method to start a session and signInForm method to redirect to profile if user is signed in.
 * 4    eok     031119  Moved signOut to here from AdvProfileController.
 *
 */

@Controller
public class AdvSignInController {

    private AdventurerService adventurerService;

    @Autowired
    public AdvSignInController(AdventurerService adventurerService) {
        this.adventurerService = adventurerService;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signInForm(Adventurer adventurer, HttpSession session) {
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if (sessionAdventurer == null) {
            return "signin";
        } else {
            return "redirect:/profile";
        }
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signIn(Adventurer adventurer, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "signin";
        }

        Adventurer exists = adventurerService.signIn(adventurer);
        if (exists == null) {
            model.addAttribute("error", "Wrong credentials.");
            return "signin";
        } else {
            //Successful sign in, starts session and redirects to profile
            session.setAttribute("SignedInAdventurer", exists);
            return "redirect:/profile";
        }
    }


    //Ends current session and redirects to signin
    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public String signOut(HttpSession session){
        session.setAttribute("SignedInAdventurer", null);
        return "redirect:/signin";
    }
}