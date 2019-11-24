package is.hi.hbv501g.agb.AGB.Services.Interfaces;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Entities.Review;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

/**
 * Programmers:
 * id   name            email
 * jgs  Jónas G.        jgs7@hi.is
 *
 * Changes:
 * no.  idProg  date    description
 * 1.    jgs    031119  Made the service Interface
 */

public interface ReviewService {

    Review save(Review review);
    void delete(Review review);
    List<Review> findAll();
    Review createReview(Review review, Adventurer adventurer, Guide guide) throws SQLIntegrityConstraintViolationException;
    Optional<Review> findById(long id); //Þetta verður sennilega leit eftir guideId
    List<Review> findByGuide(Guide guide);
    List<Review> findByAdventurerAndGuide(Adventurer adventurer, Guide guide);
}
