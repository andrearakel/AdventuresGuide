package is.hi.hbv501g.agb.AGB.Controllers;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Entities.Review;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * jgs  Jónas G.        jgs7@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    jgs     221119  Making the subsite "/createreview"
 */



@Controller
public class ReviewCreationController {

    private ReviewService reviewService;

    @Autowired
    public ReviewCreationController(ReviewService reviewService) { this.reviewService = reviewService; }

    @RequestMapping(value = "/createreview", method = RequestMethod.GET)
    public String createReviewForm(Review review, Model model, HttpSession session) {
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if (sessionAdventurer == null) {
            model.addAttribute("error", "Must be signed in to create a review.");
            return "redirect:/signin";
        } else {
            return "createreview";
        }
    }

    @RequestMapping(value = "/createreview", method = RequestMethod.POST)
    public  String createReview(@Valid Review review, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "redirect:/signin";
        }
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if (sessionAdventurer == null) {
            model.addAttribute("error", "Must be signed in to create a review.");
            return "redirect:/signin";
        }

        reviewService.createReview(review);
        return "redirect:/guides"; // Velja rétt redirect seinna meir
    }
}
