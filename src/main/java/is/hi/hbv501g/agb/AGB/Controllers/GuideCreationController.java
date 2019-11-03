package is.hi.hbv501g.agb.AGB.Controllers;

import is.hi.hbv501g.agb.AGB.Entities.Guide;
import org.springframework.stereotype.Controller;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
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
 * Changes:
 * no.  idProg  date    descriptio
 * 1.   jgs     31119   Making /createguide appear
 */




@Controller
public class GuideCreationController {

    private GuideService guideService;

    @Autowired
    public GuideCreationController(GuideService guideService){ this.guideService = guideService; }


    /**
     * Displays a Guide creation Form for the user.
     * @param guide
     * @return a CreateGuide Form for the platform. Redirects the user to guides.
     */
    @RequestMapping(value = "/createguide", method = RequestMethod.GET)
    public String createGuideForm(Guide guide){
        return "guidecreation";
    }


}
