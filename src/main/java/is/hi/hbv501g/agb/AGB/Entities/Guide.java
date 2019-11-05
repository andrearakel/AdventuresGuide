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
<<<<<<< HEAD
 * 3    eok     031119  Added getters and setters.
=======
 * 3    ars     221019  Added getters and setters, override toString (html).
>>>>>>> 29e5be3d768bfcd8a0c558824ea0996f3a7f3ff7
 * 4    jgs     041119  Added a new constructor that takes in all arguments for guide creation
 */

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Guide {
    /**
     * Stores information about the Guide.
     * Contains fields from first ERD.
     * TODO: More fields may have to be added.
     * TODO: Must add constructor and getters and setters.
     */
    @Id // Make the id unique
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // TODO: Must implement foreign key relation
    private long idAdventurer; // creator

    // TODO: Validate this (replicated from support session)
    @ElementCollection(targetClass=Template.class)
    @Column(name="template", nullable=false)
    @CollectionTable(name="guide_templates", joinColumns= {@JoinColumn(name="guide_id")})
    public Set<Template> templates;

    private String title;
    private String description;

    private boolean childFriendly;
    private boolean wheelchairAccessible;
    private int difficulty;

    private String country;
    private String state;
    private String city;
    private String directions; // how to get there

    // TODO: Could use some Location variable, but that's probably not supported by postgres.
    private double latitude;
    private double longitude;

    private int ratingAvg; // TODO: Average rating, kept on scale 1-100

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdAdventurer() {
        return idAdventurer;
    }

    public void setIdAdventurer(long idAdventurer) {
        this.idAdventurer = idAdventurer;
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
        return ratingAvg;
    }

    public void setRatingAvg(int ratingAvg) {
        this.ratingAvg = ratingAvg;
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
    public Guide(long id, long idAdventurer, String title, HashSet<Template> templates) {
        this.id = id;
        this.idAdventurer = idAdventurer;
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
    public Guide(long id, String title, String description, boolean childFriendly, boolean wheelchairAccessible, int difficulty, String directions,  String country){
        this.id = id;
        this.title = title;
        this.description = description;
        this.childFriendly = childFriendly;
        this.wheelchairAccessible = wheelchairAccessible;
        this.difficulty = difficulty;
        this.directions = directions;
        this.dateCreated = new Date();
        this.country = country;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
