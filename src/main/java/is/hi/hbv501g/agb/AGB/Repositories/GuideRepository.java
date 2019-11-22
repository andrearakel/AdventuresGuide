package is.hi.hbv501g.agb.AGB.Repositories;

import is.hi.hbv501g.agb.AGB.Entities.Guide;

import java.util.*;

import is.hi.hbv501g.agb.AGB.Entities.Template;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * jgs  Jónas G.        jgs7@hi.is
 * ars  Andrea Rakel    ars59@hi.is
 *
 * Changes:
 * no.  idProg  date    description
 * 1     ars    221019   Connect with database functions
 * 2     ars    011119   More database functions, with sql-query's
 * 3     jgs    031119   Added save, delete an findAll methods.
 * 4     ars    071119   Errors in SQL query - commented out while not fixed.
 */


public interface GuideRepository extends JpaRepository<Guide, Long> {

    Guide save(Guide guide);

    void delete(Guide guide);

    List<Guide> findAll();

    List<Guide> findByTitleContainingIgnoreCase(String title);

    Optional<Guide> findById(long id);

    List<Guide> findByTemplates(Set<Template> templates);

    List<Guide> findByCountryContainingIgnoreCase(String country);

    List<Guide> findByTitleIgnoreCaseContainingAndCountryIgnoreCaseContaining(String title, String country);


    }
