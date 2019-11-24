package is.hi.hbv501g.agb.AGB.Controllers;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * jgs  Jónas G.        jgs7@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1.   jgs     221119  Making reviews in the database visable on the site
 */

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Entities.Review;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class ReviewViewController {

    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    public String reviews(Model model, HttpSession session) {
        Adventurer sessionAdventurer = (Adventurer) session.getAttribute("SignedInAdventurer");
        if (sessionAdventurer != null) {
            model.addAttribute("sessionAdventurer", sessionAdventurer);
        }
        model.addAttribute("reviews", reviewService.findAll());
        return "reviews";
    }
}
