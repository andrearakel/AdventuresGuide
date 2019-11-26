package is.hi.hbv501g.agb.AGB.Services.Implementations;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Entities.Review;
import is.hi.hbv501g.agb.AGB.Repositories.ReviewRepository;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Programmers:
 * id   name            email
 * jgs  Jónas G.        jgs7@hi.is
 * eok  Erling Oskar    eok4@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1.   jgs     221119  Made the class, connecting to the Repository
 * 2    eok     241119  Moved business logic to createReview function. Added to it.
 * 3    eok     241119  Added constraint to createReview: Only one review per guide per user.
 * 4    eok     261119  Added comments and removed unused functions.
 */

/**
 * Invokes methods from the ReviewRepository.
 * Takes care of business logic and functionality which is not provided by the repository.
 */
@Service
public class ReviewServiceImplementation implements ReviewService {

    ReviewRepository repository;

    // Constructor
    @Autowired
    public ReviewServiceImplementation(ReviewRepository reviewRepository) {
        this.repository = reviewRepository;
    }

    @Override
    public Review save(Review review) { return repository.save(review); }

    @Override
    public void delete(Review review) { repository.delete(review); }

    @Override
    public  List<Review> findAll() { return repository.findAll(); }

    /***
     *
     * @param review The review holding the user input
     * @param adventurer The adventurer who is making the review
     * @param guide The guide that the review belongs to
     * @return the new review with unique id
     */
    @Override
    public Review createReview(Review review, Adventurer adventurer, Guide guide) throws SQLIntegrityConstraintViolationException {

        // Check if this adventurer has already reviewed this guide
        if (!findByAdventurerAndGuide(adventurer, guide).isEmpty()) {
            throw new SQLIntegrityConstraintViolationException("Adventurer has already reviewed this guide.");
        }

        Review newReview = new Review();

        // Proclaim who's reviewing what.
        newReview.setGuide(guide);
        newReview.setAdventurer(adventurer);

        // Copy review info to new review.
        newReview.setTitle(review.getTitle());
        newReview.setDescription(review.getDescription());
        newReview.setRating(review.getRating());

        newReview.setDateCreated(new Date(System.currentTimeMillis()));

        return save(newReview);
    }

    @Override
    public  Optional<Review> findById(long id) { return repository.findById(id); }

    @Override
    public List<Review> findByGuide(Guide guide) {
        return repository.findByGuide(guide);
    }

    @Override
    public List<Review> findByAdventurerAndGuide(Adventurer adventurer, Guide guide) {
        return repository.findByAdventurerAndGuide(adventurer, guide);
    }
}
