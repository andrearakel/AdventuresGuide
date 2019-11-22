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
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Review {

    /**
     * Stores information about the Adventurer.
     * Contains fields from first ERD.
     * TODO: More fields may have to be added.
     * TODO: Must add constructor and getters and setters.
     */

    @Id // Make the id unique
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long idGuide;

    private long idAdventurer;
    private String adventurerDisplayName;

    private String title; // optional
    private String description; // mandatory if title is not empty?
    private int rating; // 1-100

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

    // Simple BETA constructor
    public Review(String title,String desctiption, int rating) {
        this.id = id;
        this.idAdventurer = 1;  // Bráðabyrgða, á meðan ég er að fá þetta til að virka
        this.adventurerDisplayName = "Test";
        this.idGuide = 1;
        this.title = title;
        this.description = desctiption;
        this.rating = rating;
    }

    @Override
    public String toString() { return this.title; }
}
