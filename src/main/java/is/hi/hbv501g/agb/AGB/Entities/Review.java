package is.hi.hbv501g.agb.AGB.Entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    eok     171019  Created JavaBean entity. Contains fields from first ERD + rating.
 *                      Rating must be mandatory, but title and description optional.
 * 2
 */


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


}
