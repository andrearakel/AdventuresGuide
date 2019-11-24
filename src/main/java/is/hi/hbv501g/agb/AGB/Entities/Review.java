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
 * 2    jgs     221119  Connecting to the Repository
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
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

    @Size(max=255)
    private String title; // optional
    @Size(max=255)
    private String description; // mandatory if title is not empty?
    private int rating = 3;

    private Date dateCreated;
    private boolean enabled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdGuide() {
        return idGuide;
    }

    public void setIdGuide(long idGuide) {
        this.idGuide = idGuide;
    }

    public long getIdAdventurer() {
        return idAdventurer;
    }

    public void setIdAdventurer(long idAdventurer) {
        this.idAdventurer = idAdventurer;
    }

    public String getAdventurerDisplayName() {
        return adventurerDisplayName;
    }

    public void setAdventurerDisplayName(String adventurerDisplayName) {
        this.adventurerDisplayName = adventurerDisplayName;
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

    public Review(long id, long idAdventurer, long idGuide, String title, String description, int rating) {
        this.id = id;
        this.idAdventurer = idAdventurer;
        this.idGuide = idGuide;

        this.title = title;
        this.description = description;
        this.rating = rating;
    }


    @Override
    public String toString() { return this.title; }
}
