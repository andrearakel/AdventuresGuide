package is.hi.hbv501g.agb.AGB.Services.Interfaces;

import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Entities.Template;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * ars  Andrea Rakel    ars59@hi.is
 * jgs  Jónas G.        jgs7@hi.is
 *
 * Changes:
 * no.  idProg  date    description
 * 1.    ars    221019  Connect with database functions
 * 2.    ars    021119  Extra search function
 * 3.    jgs    031119  Added save, delete, findAll and createGuide methods
 */


public interface GuideService {

    Guide save(Guide guide);
    void delete(Guide guide);
    List<Guide> findAll();
    Guide createGuide(Guide guide);
    Optional<Guide> findById(long id);
    List<Guide> findByTitleContainingIgnoreCase(String title);
    List<Guide> findByTemplates(Set<Template> templates);
    List<Guide> findByCountryContainingIgnoreCase(String country);
    List<Guide> findByTitleIgnoreCaseContainingAndCountryIgnoreCaseContaining(String title, String country);



    }
