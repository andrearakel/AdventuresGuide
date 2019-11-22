package is.hi.hbv501g.agb.AGB.Services.Implementations;

import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Entities.Review;
import is.hi.hbv501g.agb.AGB.Repositories.ReviewRepository;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public Review createReview(Review review) {
        return save(review);
    }

    @Override
    public  Optional<Review> findById(long id) { return  repository.findById(id); }

}
