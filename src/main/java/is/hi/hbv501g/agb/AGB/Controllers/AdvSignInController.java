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

@Controller
public class AdvSignInController {

    private AdventurerService adventurerService;

    @Autowired
    public AdvSignInController(AdventurerService adventurerService){ this.adventurerService = adventurerService; }

    @RequestMapping(value ="/signin", method = RequestMethod.GET)
    public String signInForm(Adventurer adventurer){ return "signin"; }

    @RequestMapping(value ="/signin", method = RequestMethod.POST)
    public String signIn(Adventurer adventurer, @ModelAttribute("email") String email, @ModelAttribute("password") String password, BindingResult result, Model model){
        if(result.hasErrors()){
            return "signin";
        }

        Adventurer exists = adventurerService.signIn(adventurer);
        if (exists == null) {
            model.addAttribute("error", "Wrong email or password");
            return "signin";
        }
        return "redirect:/profile";

        /*
        Optional<Adventurer> optAdv = adventurerService.findByEmail(email);

        if(optAdv.isPresent()){
            if(optAdv.get().getPasswordHashed().equals(password)){
                //Successful login
                //TODO: Start session
                return "redirect:/profile";
            }
            else {
                model.addAttribute("error", "Wrong password");
                return "signin";
            }
        }
        else {
            model.addAttribute("error","Wrong email");
            return "signin";
        }
        */
    }
}
