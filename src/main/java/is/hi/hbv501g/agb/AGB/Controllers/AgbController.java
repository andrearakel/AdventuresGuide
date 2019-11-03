package is.hi.hbv501g.agb.AGB.Controllers;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 */


import is.hi.hbv501g.agb.AGB.Services.Interfaces.AdventurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AgbController {

    private AdventurerService adventurerService;

    @Autowired
    public AgbController(AdventurerService adventurerService){ this.adventurerService = adventurerService; }

    @RequestMapping(value = "/adventurers", method = RequestMethod.GET)
    public String usersGET(Model model){
        model.addAttribute("adventurers", adventurerService.findAll());
        return "adventurers";
    }
}
