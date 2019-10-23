package is.hi.hbv501g.agb.AGB.Services.Interfaces;

import is.hi.hbv501g.agb.AGB.Entities.Guide;

import java.util.List;
import java.util.Optional;

/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * ars  Andrea Rakel    ars59@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1.    ars    221019   Connect with database functions
 */


public interface GuideService {
    Guide save(Guide guide);
    void delete(Guide guide);
    List<Guide> findAll();
    Optional<Guide> findById(long id);
    List<Guide> findByTitle(String title);


}
