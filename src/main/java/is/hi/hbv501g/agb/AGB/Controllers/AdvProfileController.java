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
     * @return the profile page of the adventurer who is signed in. Redirects to signIn if no one is signed in.
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

        model.addAttribute("adventurer", sessionAdventurer);
        return "profile";
    }



    /**
     * @param adventurer
     * @param result
     * @param model
     * @param session
     * @return A page where the signed in adventurer can edit his profile. Redirects to signIn if no one is signed in.
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

        model.addAttribute("adventurer", sessionAdventurer);
        return "editprofile";
    }

    /**
     * @param adventurer
     * @param result
     * @param model
     * @param session
     * @return A page where the signed in adventerer can edit his profile.
     * Redirects to signIn if no one is signed in.
     * Redirect to profile page is exceptions are caught.
     */
    @RequestMapping(value ="/updateprofile", method = RequestMethod.POST)
    public String updateProfile(@Valid Adventurer adventurer, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            System.out.println("error in updateProfile");
            return "editprofile";
        }
        // Check that Adventurer is signed in
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if(sessionAdventurer == null){
            return "redirect:/signin";
        }

        // Save the edited adventurer
        try {
            adventurerService.updateProfile(adventurer, sessionAdventurer);
        // Redirect to profile page if exceptions are caught. Changes are not saved.
            // TODO: Deal with errors/exceptions in adventurerService.updateProfile(..)
        } catch (DataIntegrityViolationException e) {
            return "editprofile";
        } catch (NullPointerException npe) { }

        return "redirect:/profile";

    }
}
