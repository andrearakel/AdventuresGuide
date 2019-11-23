package is.hi.hbv501g.agb.AGB.Controllers;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Entities.Review;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.ReviewService;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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
 * 2    jgs     231119  Adding "createreview/{id}", review goes to the database with a correct idGuide
 */



@Controller
public class ReviewCreationController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    GuideService guideService;

    @Autowired
    public ReviewCreationController(ReviewService reviewService) { this.reviewService = reviewService; }

    @RequestMapping(value = "/createreview/{id}", method = RequestMethod.GET)
    public String createReviewForm(@PathVariable("id") long id, Review review, Model model, HttpSession session) {
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if (sessionAdventurer == null) {
            model.addAttribute("error", "Must be signed in to create a review.");
            return "redirect:/signin";
        } else {
            Guide guide = guideService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid guide ID"));
            model.addAttribute("guide", guide);
            return "createreview";
        }
    }

    @RequestMapping(value = "/createreview/{id}", method = RequestMethod.POST)
    public  String createReview(@PathVariable("id") long id, @Valid Review review, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "redirect:/signin";
        }
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if (sessionAdventurer == null) {
            model.addAttribute("error", "Must be signed in to create a review.");
            return "redirect:/signin";
        }
        review.setIdGuide(id);
        reviewService.createReview(review);
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
