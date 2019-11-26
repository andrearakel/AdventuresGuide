package is.hi.hbv501g.agb.AGB.Entities;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * jgs  Jónas G.        jgs7@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    eok     171019  Created JavaBean entity. Contains fields from first ERD + rating.
 *                      Rating must be mandatory, but title and description optional.
 * 2    jgs     221119  Connecting to the Repository
 * 3    eok     241119  Implemented foreign key relations to Guide and Adventurer
 * 4    eok     261119  Added comments and removed unused functions.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Represents a review, a review of a guide, created by an adventurer.
 */
@Entity
public class Review {
    /**
     * Stores information about the Review.
     * Contains fields from first ERD.
     */

    @Id // Unique Id, primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Each guide can have many reviews.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guide_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Guide guide;

    // Each adventurer can have many reviews (but only one per guide, restricted in GuideService).
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adventurer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Adventurer adventurer;

    @Size(max=255)
    private String title;
    @Size(max=255)
    private String description;
    private int rating = 0;

    private Date dateCreated;
    private boolean enabled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Review() { } // Java Beans: one constructor must be empty

    public Review(long id, String title, String description, int rating) {
        this.id = id;

        this.title = title;
        this.description = description;
        this.rating = rating;
    }


    @Override
    public String toString() { return this.title; }

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }

    public void setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
    }
}
