package is.hi.hbv501g.agb.AGB.Controllers;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.AdventurerService;
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
 * boj  Bjartur         boj8
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    eok     171019  Initialized controller. Created signUpForm and signUp methods.
 * 2    eok     311019  Caught DataIntegrityViolationException upon illegal signup. Added "redirect:/".
 * 3    boj     011119  Changed signUpForm method to redirect to profile if user is signed in.
 * 4    eok     021119  Improved error messages on failed signUp. Added javadoc.
 * 5    eok     261119  Added comments and removed unused functions.
 */

/**
 * Controller that takes care of creating (and deleting) adventurers, allowing them to sign up for the platform.
 */
@Controller
public class AdvSignUpController {

    private AdventurerService adventurerService;

    @Autowired
    public AdvSignUpController(AdventurerService adventurerService){ this.adventurerService = adventurerService; }

    /**
     * Displays a SignUp Form for the user.
     * @param adventurer
     * @param session The current session in process.
     * @return a SignUp Form for the platform.
     * Redirects the user to their profile if they are already logged in.
     */
    @RequestMapping(value ="/signup", method = RequestMethod.GET)
    public String signUpForm(Adventurer adventurer, HttpSession session){
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if (sessionAdventurer == null) { return "signup"; }
        else { return "redirect:/profile"; }
    }


    /**
     * Invokes the AdventurerService SignUp method in order to create a adventurer in the system, using the user input.
     * @param adventurer being created from user supplied displayName, email and password.
     * @param result of model validation.
     * @param model
     * @return Directs the user to the signin page if signUp is successful,
     * otherwise refreshes this page with appropriate error message.
     */
    @RequestMapping(value ="/signup", method = RequestMethod.POST)
    public String signUp(@Valid Adventurer adventurer, BindingResult result, Model model) {
        if (result.hasErrors()) { return "signup"; }
        try {
            // Save the new adventurer
            adventurerService.signUp(adventurer);
        } catch (DataIntegrityViolationException e) {
            // Check what constraint was violated, display error message.
            if (adventurerService.findByEmail(adventurer.getEmail()).isPresent()) {
                model.addAttribute("error", "An adventurer already exists with the Email: " + adventurer.getEmail() );
            }
            else {
                model.addAttribute("error", "An adventurer already exists with the DisplayName: " + adventurer.getDisplayName() );
            }
            return "signup";
        }

        // Direct to sign in page
        return "redirect:/signin";
    }

}
