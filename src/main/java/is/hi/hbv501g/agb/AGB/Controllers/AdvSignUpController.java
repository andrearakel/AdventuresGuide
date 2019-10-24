package is.hi.hbv501g.agb.AGB.Controllers;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.AdventurerService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    eok     171019  Initialized controller. Created signUpForm and signUp methods.
 */

@Controller
public class AdvSignUpController {

    private AdventurerService adventurerService;

    @Autowired
    public AdvSignUpController(AdventurerService adventurerService){ this.adventurerService = adventurerService; }

    @RequestMapping(value ="/signup", method = RequestMethod.GET)
    public String signUpForm(Adventurer adventurer){ return "signup"; }


    @RequestMapping(value ="/signup", method = RequestMethod.POST)
    public String signUp(@Valid Adventurer adventurer, BindingResult result, Model model){
        if(result.hasErrors()){
            // TODO: Deal with error
            return "signup";
        }
        // Save the new adventurer
        adventurerService.save(adventurer);

        // Direct to his profile
        return "profile";
    }

}
