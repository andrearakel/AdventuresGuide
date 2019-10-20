package is.hi.hbv501g.agb.AGB.Repositories;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Programmers:
 * id   name            email
 * eok  Erling Oskar    eok4@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    eok     171019  Added save, delete and findByEmail methods.
 */


public interface AdventurerRepository extends JpaRepository<Adventurer, Long> {

    Adventurer save(Adventurer adventurer);
    void delete(Adventurer adventurer);
    Optional<Adventurer> findByEmail(String email);
}
