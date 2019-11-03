package is.hi.hbv501g.agb.AGB.Services.Interfaces;

import is.hi.hbv501g.agb.AGB.Entities.Guide;

import java.util.List;

/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * jgs  Jónas G.        jgs7@hi.is
 *
 * Changes:
 * no.  idProg  date    description
 * 1    jgs     031119  Added save, delete, findAll and createGuide methods
 */


public interface GuideService {

    Guide save(Guide guide);
    void delete(Guide guide);
    List<Guide> findAll();
    Guide createGuide(Guide guide);
}
