package is.hi.hbv501g.agb.AGB.Entities;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    eok     151019  Created javabean entity with fields from ERD.
 * 2    eok     171019  Added constructor, setters and getters.
 * 3    eok     241019  Made email and displayName unique. Started playing around with column nullable and length properties.
 */

import javax.persistence.*;
import java.util.Date;

@Entity
public class Adventurer {
    /**
     * Stores information about the Adventurer.
     * Contains fields from first ERD.
     *
     * TODO: More fields may have to be added.
     */

    @Id // Make the id unique
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // TODO: Look into password protection.
    private String passwordSalt;
    private String passwordHashed;

    @Column(unique=true, nullable = false)
    private String email;
    @Column(unique=true, nullable = false, length = 50) // length and nullable don't seem to be working.
    private String displayName;
    private String name;
    private String biography;
    private Date dateOfBirth;

    private String country;
    private String state;
    private String city;

    private Date dateCreated;
    private boolean enabled;

    public Adventurer() { } // Java Beans: one constructor must be empty

    // First basic constructor
    public Adventurer(String passwordHashed, String email, String displayName) {
        this.passwordHashed = passwordHashed;
        this.email = email;
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    /***** GETTERS AND SETTERS *****/
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // TODO: Reconsider public password and salt.
    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getPasswordHashed() {
        return passwordHashed;
    }

    public void setPasswordHashed(String passwordHashed) {
        this.passwordHashed = passwordHashed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
}
