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
 * 3    eok     021119  Added editProfile and updateProfile methods.
 * 4    eok     031119  Moved signOut from here to AdvSignInController.
 * 5    eok     261119  Added comments and removed unused functions.
 */


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
 * This controller takes care of anything to do with the Adventurer's Profile.
 */
@Controller
public class AdvProfileController {

    private AdventurerService adventurerService;

    @Autowired
    AdvSignInController advSignInController;

    @Autowired
    public AdvProfileController(AdventurerService adventurerService){ this.adventurerService = adventurerService; }

    /**
     * @param adventurer
     * @param result
     * @param model
     * @param session
     * @return the profile page of the adventurer who is signed in.
     * Redirects to signIn if no one is signed in.
     */
    @RequestMapping(value ="/profile", method = RequestMethod.GET)
    public String viewProfile(@Valid Adventurer adventurer, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            // Redirect to signin
            return "redirect:/signin";
        }
        // Check that Adventurer is signed in
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if(sessionAdventurer == null){
            model.addAttribute("error", "Please sign in to view your profile.");
            return advSignInController.signInForm(adventurer, session);
        }
        model.addAttribute("sessionAdventurer", sessionAdventurer); // Fyrir menubar
        model.addAttribute("adventurer", sessionAdventurer);
        return "profile";
    }



    /**
     * @param adventurer
     * @param result
     * @param model
     * @param session
     * @return A page where the signed in adventurer can edit his profile.
     * Redirects to signIn if no one is signed in.
     */
    @RequestMapping(value ="/editprofile", method = RequestMethod.GET)
    public String editProfileForm(@Valid Adventurer adventurer, BindingResult result, Model model, HttpSession session){
        // Check that Adventurer is signed in
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if(sessionAdventurer == null){
            return "redirect:/signin";
        }
        if(result.hasErrors()){
            // Do nothing, simply show the page which will reveal the errors.
            model.addAttribute("error", result.getAllErrors().get(0).getDefaultMessage());
        }

        model.addAttribute("sessionAdventurer", sessionAdventurer);
        model.addAttribute("adventurer", sessionAdventurer);
        return "editprofile";
    }

    /**
     * @param adventurer holds the input from the adventurer, i.e. the fields that were just changed.
     * @param result
     * @param model
     * @param session
     * @return the viewProfile page
     * Redirects to signIn if no one is signed in.
     * Redirect back to edit profile page is exceptions are caught or input is invalid; displays appropriate errors.
     */
    @RequestMapping(value ="/updateprofile", method = RequestMethod.POST)
    public String updateProfile(@Valid Adventurer adventurer, BindingResult result, Model model, HttpSession session){

        // Check that Adventurer is signed in
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if(sessionAdventurer == null){ return "redirect:/signin"; }
        model.addAttribute("sessionAdventurer", sessionAdventurer);

        if(result.hasErrors()){
            System.out.println("result.hasErrors()");
            // add uneditable fields that were not on the form to the adventurer
            adventurer.setDisplayName(sessionAdventurer.getDisplayName());
            adventurer.setEmail(sessionAdventurer.getEmail());
            return "editprofile";
        }

        try {
            // Update the old adventurer with input from the form
            adventurerService.updateProfile(adventurer, sessionAdventurer);
        } catch (DataIntegrityViolationException e) {
            System.out.println("DataIntegrityViolationException");
            model.addAttribute("adventurer", sessionAdventurer);
            return "editprofile";
        } catch (NullPointerException npe) { }

        return "redirect:/profile";

    }

}
