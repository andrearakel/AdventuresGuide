package is.hi.hbv501g.agb.AGB.Repositories;

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
 *
 */


public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review save(Review review);

    void delete(Review review);

    List<Review> findAll();

    Optional<Review> findById(long id);

}
