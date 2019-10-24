package is.hi.hbv501g.agb.AGB.Controllers;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    eok     171019  Created controller with a basic viewProfile method. Will be changed.
 */


import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.AdventurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AdvProfileController {

    private AdventurerService adventurerService;

    @Autowired
    public AdvProfileController(AdventurerService adventurerService){ this.adventurerService = adventurerService; }

    // TODO: Change so the method takes the email as parameter, or uses current session id or something.
    @RequestMapping(value ="/profile", method = RequestMethod.GET)
    public String viewProfile(@Valid Adventurer adventurer, BindingResult result, Model model){
        if(result.hasErrors()){
            // TODO: Deal with error
            return "signup";
        }
        Optional<Adventurer> optAdv = adventurerService.findByEmail("test@test");
        if(optAdv.isPresent()){
            System.out.println("opt adv is present: " + optAdv.toString());
            model.addAttribute("adventurer", optAdv.get());
        }
        else {
            // Following 2 lines are actually just equivalent to "return "signup" (at least for now)
            AdvSignUpController advSignUpController = new AdvSignUpController(adventurerService);
            return advSignUpController.signUpForm(adventurer);
        }

        return "profile";
    }
}
