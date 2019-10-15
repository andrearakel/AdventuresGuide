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
    // TODO: Can start with 1 default Template
    private long idTemplate;
    private String titleTemplate; // title of template

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

    private int ratingAvg; // TODO: Average rating, kept on scale 0-100

    private Date dateCreated;
    private boolean enabled;

    public Guide() { } // Java Beans: one constructor must be empty
}
