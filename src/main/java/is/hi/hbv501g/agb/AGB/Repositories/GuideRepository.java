package is.hi.hbv501g.agb.AGB.Repositories;

import is.hi.hbv501g.agb.AGB.Entities.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * jgs  Jónas G.        jgs7@hi.is
 *
 * Changes:
 * no.  idProg  date    description
 * 1    jgs     031119  Added save, delete an findAll methods.
 */


public interface GuideRepository extends JpaRepository<Guide, Long> {
    Guide save(Guide guide);
    void delete(Guide guide);
    List<Guide> findAll();
}
