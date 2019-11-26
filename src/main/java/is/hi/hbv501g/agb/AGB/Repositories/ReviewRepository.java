package is.hi.hbv501g.agb.AGB.Repositories;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Entities.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
import java.util.List;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * jgs  Jónas G.        jgs7@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1.   jgs     221119  Innitial connection to the ReviewServiceImplementation
 * 2.   jgs     231119  Added findByIdGuide
 *
 */


public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review save(Review review);
    void delete(Review review);
    Optional<Review> findById(long id);
    List<Review> findAll();
    List<Review> findByGuide(Guide guide);
    List<Review> findByAdventurerAndGuide(Adventurer adventurer, Guide guide);
}
