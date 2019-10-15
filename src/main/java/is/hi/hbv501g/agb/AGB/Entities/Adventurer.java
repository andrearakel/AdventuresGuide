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
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Adventurer {
    /**
     * Stores information about the Adventurer.
     * Contains fields from first ERD.
     * TODO: More fields may have to be added.
     * TODO: Must add constructor and getters and setters.
     */

    @Id // Make the id unique
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // TODO: Look into password protection.
    private String passwordSalt;
    private String passwordHashed;

    private String email;
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
}
