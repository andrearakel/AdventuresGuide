package is.hi.hbv501g.agb.AGB.Controllers;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * ars  Andrea Rakel    ars59@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1     ars    021119  Added a search method for the search bar.
 * 2     ars    071119  More efficient way of search method
 * 3     ars    221119  Added back to last search
 */


import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GuideSearchController {

    private GuideService guideService;

    private List<Guide> lastSearch;

    @Autowired
    public GuideSearchController(GuideService guideService) { this.guideService = guideService; }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String SearchGet(Model model){
        model.addAttribute("guides", guideService.findAll());
        return "home";
    }


    /**
     * A search method that makes a list of guides depending on what the user searches for.
     * @param title
     * @param country
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String searchGuide(@RequestParam(value = "title") String title,
                              @RequestParam(value = "country") String country,
                              Model model) {

            List<Guide> searchList =
                    guideService.findByTitleIgnoreCaseContainingAndCountryIgnoreCaseContaining
                            (title, country);

            lastSearch = searchList;

            model.addAttribute("guides", searchList);
            return "home";

    }

    @RequestMapping(value = "/backToLastSearch")
    public String backToLastSearch(Model model) {
        if (lastSearch.isEmpty()) {
            model.addAttribute("guides", guideService.findAll());
        }
        else{
            model.addAttribute("guides", lastSearch);
        }
        return "redirect:/";
    }

}
