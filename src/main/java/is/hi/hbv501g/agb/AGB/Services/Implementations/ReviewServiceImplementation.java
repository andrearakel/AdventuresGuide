package is.hi.hbv501g.agb.AGB.Services.Implementations;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Entities.Review;
import is.hi.hbv501g.agb.AGB.Repositories.ReviewRepository;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Programmers:
 * id   name            email
 * jgs  Jónas G.        jgs7@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1.   jgs     221119  Made the class, connecting to the Repository
 */

@Service
public class ReviewServiceImplementation implements ReviewService {

    ReviewRepository repository;

    // Constructor
    @Autowired
    public ReviewServiceImplementation(ReviewRepository reviewRepository) { this.repository = reviewRepository; }

    @Override
    public Review save(Review review) { return repository.save(review); }

    @Override
    public void delete(Review review) { repository.delete(review); }

    @Override
    public  List<Review> findAll() { return repository.findAll(); }

    /***
     *
     * @param review The review being created
     * @param adventurer The adventurer who is making the review
     * @param guideId The id of the guide that the review belongs to
     * @return the new review
     */
    @Override
    public Review createReview(Review review, Adventurer adventurer, long guideId) {

        review.setDateCreated(new Date(System.currentTimeMillis()));

        review.setIdGuide(guideId);
        review.setIdAdventurer(adventurer.getId());
        review.setAdventurerDisplayName(adventurer.getDisplayName());

        return save(review);
    }

    @Override
    public  Optional<Review> findById(long id) { return  repository.findById(id); }

    @Override
    public List<Review> findByIdGuide(long idGuide) {
        return repository.findByIdGuide(idGuide);
    }
}
