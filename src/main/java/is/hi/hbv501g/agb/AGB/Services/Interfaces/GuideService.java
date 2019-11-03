package is.hi.hbv501g.agb.AGB.Services.Interfaces;

import is.hi.hbv501g.agb.AGB.Entities.Guide;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
<<<<<<< HEAD
 * jgs  Jónas G.        jgs7@hi.is
 *
 * Changes:
 * no.  idProg  date    description
 * 1    jgs     031119  Added save, delete, findAll and createGuide methods
=======
 * ars  Andrea Rakel    ars59@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1.    ars    221019   Connect with database functions
 * 2.    ars    021119   Extra search function
>>>>>>> 29e5be3d768bfcd8a0c558824ea0996f3a7f3ff7
 */


public interface GuideService {

    Guide save(Guide guide);
    void delete(Guide guide);
    List<Guide> findAll();
    Guide createGuide(Guide guide);
    Optional<Guide> findById(long id);
    List<Guide> findByTitle(String title);
    //List<Guide> findByTemplate(EnumSet templates);
    List<Guide> findByLocation(String country);
    List<Guide> findByMatches(Guide guide);

}
