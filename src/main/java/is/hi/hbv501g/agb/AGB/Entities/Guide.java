package is.hi.hbv501g.agb.AGB.Entities;
/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * ars  Andrea Rakel    ars59@hi.is
 *
 * Changes:
 * no.  idProg  date    description
 * 1    eok     151019  Created javabean entity with fields from ERD.
 * 2    eok     171019  Changed to use Template enum, added a basic constructor.
 * 3    ars     221019  Added getters and setters, override toString (html).
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

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) {this.title = title; }

    public long getIdAdventurer() {return idAdventurer; }

    public void setIdAdventurer(long idAdventurer) {this.idAdventurer = idAdventurer; }


    @Override
    public String toString() {
        return this.title;
    }
}
