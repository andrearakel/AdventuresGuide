package is.hi.hbv501g.agb.AGB.Services.Implementations;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Repositories.AdventurerRepository;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.AdventurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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

@Service // Service is an extra layer between controller and repository(database) which can do more than the Repository itself.
public class AdventurerServiceImplementation implements AdventurerService {

    AdventurerRepository repository;

    // Constructor
    @Autowired
    public AdventurerServiceImplementation(AdventurerRepository adventurerRepository){ this.repository = adventurerRepository; }

    // TODO: Add DateCreated
    @Override
    public Adventurer save(Adventurer adventurer) {

        // TODO: Check that email is not in use

        // TODO: Check that displayName is not in use, perhaps restrict its length?

        // TODO: Hash password .. https://stackabuse.com/password-encoding-with-spring-security/

        return repository.save(adventurer);
    }

    @Override
    public void delete(Adventurer adventurer) {
        repository.delete(adventurer);
    }

    @Override
    public Optional<Adventurer> findByEmail(String email) {
        return repository.findByEmail(email);
    }

}
