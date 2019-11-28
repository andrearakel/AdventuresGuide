package is.hi.hbv501g.agb.AGB.Entities;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * ars  Andrea Rakel    ars59@hi.is
 * jgs  Jónas G.        jgs7@hi.is
 *
 * Changes:
 * no.  idProg  date    description
 * 1    eok     151019  Created javabean entity with fields from ERD.
 * 2    eok     171019  Changed to use Template enum, added a basic constructor.
 * 3    eok     031119  Added getters and setters.
 * 4    ars     221019  Added getters and setters, override toString (html).
 * 5    jgs     041119  Added a new constructor that takes in all arguments for guide creation
 * 6    eok     221119  Set default value for difficulty. Added length restrictions for strings.
 * 7    eok     241119  Implemented many-to-one relation between guide and adventurer, one-to-many between guide and review. Calculate average rating on retrieval from DB.
 * 8    eok     261119  Added comments and removed unused functions.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a guide, an adventure guide, created by an adventurer.
 */
@Entity
public class Guide {
    /**
     * Stores information about the Guide.
     * Contains fields from first ERD.
     */

    @Id // Unique Id, primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Each adventurer can have many guides.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "adventurer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Adventurer adventurer;

    // Each review can belong to one guide.
    @OneToMany(mappedBy = "guide",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Review> reviews;

    @ElementCollection(targetClass=Template.class)
    @Column(name="template", nullable=false)
    @CollectionTable(name="guide_templates", joinColumns= {@JoinColumn(name="guide_id")})
    private Set<Template> templates;

    @NotNull
    @Size(min=4, max=50)
    @Column(unique = true)
    private String title;
    @Size(max=250)
    private String description;

    private boolean childFriendly;
    private boolean wheelchairAccessible;
    private int difficulty = 3; // default value

    @Size(max=50)
    private String country;
    @Size(max=50)
    private String state;
    @Size(max=50)
    private String city;

    @Size(max=250)
    private String directions; // how to get there

    // TODO: Could use some Location variable, but that's probably not supported by postgres.
    private double latitude;
    private double longitude;

    private int ratingAvg;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(Set<Template> templates) {
        this.templates = templates;
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

    public boolean isChildFriendly() {
        return childFriendly;
    }

    public void setChildFriendly(boolean childFriendly) {
        this.childFriendly = childFriendly;
    }

    public boolean isWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(boolean wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getRatingAvg() {
        this.setRatingAvg();
        return ratingAvg;
    }

    public void setRatingAvg() {
        float dRatingAvg = 0;
        for(Review review : this.reviews) {
            dRatingAvg += review.getRating();
        }
        dRatingAvg /= reviews.size();
        this.ratingAvg = (Math.round(dRatingAvg));
        if (this.ratingAvg == 0) this.ratingAvg = 3;
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

    private Date dateCreated;
    private boolean enabled;

    public Guide() { } // Java Beans: one constructor must be empty

    // TODO: Consider whether guide will be created and fields added in another request, or make a constructor with more params.
    public Guide(long id, String title, HashSet<Template> templates) {
        this.id = id;
        this.title = title;
        this.templates = templates;
    }

    // Simple BETA constructor
    public Guide(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Hopefully the full constructor
    public Guide(long id, String title, String description, boolean childFriendly, boolean wheelchairAccessible, int difficulty, String directions,  String country,  String state,  String city, HashSet<Template> templates){
        this.id = id;
        this.title = title;
        this.description = description;
        this.childFriendly = childFriendly;
        this.wheelchairAccessible = wheelchairAccessible;
        this.difficulty = difficulty;
        this.directions = directions;
        this.dateCreated = new Date();
        this.country = country;
        this.state = state;
        this.city = city;
        this.templates = templates;
    }

    @Override
    public String toString() {
        return this.title;
    }


    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }

    public void setAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
    }
}
