package is.hi.hbv501g.agb.AGB.Repositories;

import is.hi.hbv501g.agb.AGB.Entities.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

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
 * 1     ars    221019   Connect with database functions
 */


public interface GuideRepository extends JpaRepository<Guide, Long> {

    Guide save(Guide guide);
    void delete(Guide guide);
    List<Guide> findAll();
    List<Guide> findByTitle(String title);
    Optional<Guide> findById(long id);


}
