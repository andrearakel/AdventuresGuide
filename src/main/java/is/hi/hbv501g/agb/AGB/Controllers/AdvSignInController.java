package is.hi.hbv501g.agb.AGB.Controllers;
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
 * 2    eok     311019  Fixed typo. Added "redirect:" in front of return statement on successful signin.
 * 3    boj     011119  Changed signIn method to start a session and signInForm method to redirect to profile if user is signed in.
 */


import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.AdventurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

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
            model.addAttribute("error", "Wrong email or password");
            return "signin";
        } else {
            //Successful sign in, starts session and redirects to profile
            session.setAttribute("SignedInAdventurer", exists);
            return "redirect:/profile";
        }
    }
}