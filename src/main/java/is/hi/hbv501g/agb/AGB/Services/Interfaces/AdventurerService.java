package is.hi.hbv501g.agb.AGB.Services.Interfaces;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;

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


public interface AdventurerService {

    Adventurer save(Adventurer adventurer);
    void delete(Adventurer adventurer);
    Optional<Adventurer> findByEmail(String email);
}
