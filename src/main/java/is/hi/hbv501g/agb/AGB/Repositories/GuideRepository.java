package is.hi.hbv501g.agb.AGB.Repositories;

import is.hi.hbv501g.agb.AGB.Entities.Guide;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 * ars  Andrea Rakel    ars59@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1     ars    221019   Connect with database functions
 * 2     ars    011119   More database functions, with sql-query's
 */


public interface GuideRepository extends JpaRepository<Guide, Long> {

    Guide save(Guide guide);
    void delete(Guide guide);
    List<Guide> findAll();

    //Case insensitive, looks for everything matching something from the string
    @Query(value = "SELECT g FROM Guide g where lower(g.title) LIKE lower(concat('%', :title, '%'))")
    List<Guide> findByTitle( @Param("title") String title);

    Optional<Guide> findById(long id);

    List<Guide> findByTemplate(EnumSet template);

    //Case insensitive, looks for country
    @Query(value = "SELECT g FROM Guide g where lower(g.country)")
    List<Guide> findByLocation(String location);
}
