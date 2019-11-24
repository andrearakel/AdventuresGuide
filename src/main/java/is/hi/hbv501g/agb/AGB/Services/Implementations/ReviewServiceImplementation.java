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
 * 2    eok     241119  Moved business logic to createReview function. Added to it.
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
     * @param guideId The id of the guide that the review belongs to
     * @return the new review with unique id
     */
    @Override
    public Review createReview(Review review, Adventurer adventurer, long guideId) {

        Review newReview = new Review();
        newReview.setTitle(review.getTitle());
        newReview.setDescription(review.getDescription());
        newReview.setRating(review.getRating());

        newReview.setDateCreated(new Date(System.currentTimeMillis()));

        newReview.setIdGuide(guideId);
        newReview.setIdAdventurer(adventurer.getId());
        newReview.setAdventurerDisplayName(adventurer.getDisplayName());

        return save(newReview);

        // TODO: Invoke method to calculate and update guide.ratingAvg ?
    }

    @Override
    public  Optional<Review> findById(long id) { return  repository.findById(id); }

    @Override
    public List<Review> findByIdGuide(long idGuide) {
        return repository.findByIdGuide(idGuide);
    }
}
