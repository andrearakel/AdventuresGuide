package is.hi.hbv501g.agb.AGB.Controllers;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Entities.Review;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

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
 * 3    eok     231119  Moved createReview business logic to service.
 */



@Controller
public class ReviewCreationController {

    private ReviewService reviewService;
    private GuideService guideService;

    @Autowired
    GuideViewController guideViewController;

    @Autowired
    public ReviewCreationController(ReviewService reviewService, GuideService guideService) {
        this.reviewService = reviewService;
        this.guideService = guideService;
    }

    @RequestMapping(value = "/createreview/{id}", method = RequestMethod.GET)
    public String createReviewForm(@PathVariable("id") long id, Review review, Model model, HttpSession session) {
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if (sessionAdventurer == null) {
            model.addAttribute("error", "Must be signed in to create a review.");
            return guideViewController.singleGuideView(id, model, session);
        }
        if (!reviewService.findByAdventurerAndGuide(sessionAdventurer, guideService.findById(id).get()).isEmpty()) {
            model.addAttribute("error", "You have already reviewed this guide.");
            return guideViewController.singleGuideView(id, model, session);
        }

        Guide guide = guideService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid guide ID"));
        model.addAttribute("sessionAdventurer", sessionAdventurer);
        model.addAttribute("guide", guide);
        return "createreview";
    }



    @RequestMapping(value = "/createreview/{id}", method = RequestMethod.POST)
    public  String createReview(@PathVariable("id") long id, @Valid Review review, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return createReviewForm(id, review, model, session);
        }
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if (sessionAdventurer == null) {
            model.addAttribute("error", "Must be signed in to create a review.");
            return "redirect:/signin";
        }

        Guide guide = guideService.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid guide ID"));

        try {
            reviewService.createReview(review, sessionAdventurer, guide);
        } catch (SQLIntegrityConstraintViolationException e1) {
            model.addAttribute("error", e1.getMessage());
            return guideViewController.singleGuideView(id, model, session);
        } catch (DataIntegrityViolationException e2) {
            model.addAttribute("error", e2.getMessage());
            return createReviewForm(id, review, model, session);
        }

        return "redirect:/guide/" + id;
    }
}
