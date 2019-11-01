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
 * 1    eok     171019  Created controller with a basic viewProfile method. Will be changed.
 * 2    boj     011119  Changed viewProfile method to show the profile of the user that's signed in and added signout button
 */


import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.AdventurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AdvProfileController {

    private AdventurerService adventurerService;

    @Autowired
    public AdvProfileController(AdventurerService adventurerService){ this.adventurerService = adventurerService; }

    //Shows the profile of the user that's signed in, redirects to signin if no one is signed in.
    @RequestMapping(value ="/profile", method = RequestMethod.GET)
    public String viewProfile(@Valid Adventurer adventurer, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            // TODO: Deal with error
            System.out.println("Error in AdvProfileController.viewProfile -> redirect to signup");
            return "redirect:/signup";
        }
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if(sessionAdventurer != null){
            model.addAttribute("adventurer", sessionAdventurer);
            return "profile";
        }

        return "redirect:/signin";
    }

    //Ends current session and redirects to signin
    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public String viewProfile(HttpSession session){
        session.setAttribute("SignedInAdventurer", null);
        return "redirect:/signin";
    }
}
