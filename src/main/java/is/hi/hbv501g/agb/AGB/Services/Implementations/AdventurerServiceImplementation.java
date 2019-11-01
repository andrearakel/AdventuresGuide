package is.hi.hbv501g.agb.AGB.Services.Implementations;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Repositories.AdventurerRepository;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.AdventurerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
 * 2    eok     311019  Finished implementing save() with password hashing and dateCreated.
 *                      Implemented findAll and signIn methods. SignIn may need some rework.
 */

@Service // Service is an extra layer between controller and repository(database) which can do more than the Repository itself.
public class AdventurerServiceImplementation implements AdventurerService {

    AdventurerRepository repository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Constructor
    @Autowired
    public AdventurerServiceImplementation(AdventurerRepository adventurerRepository){ this.repository = adventurerRepository; }

    // TODO: Add DateCreated
    @Override
    public Adventurer save(Adventurer adventurer) {

        // HTML id="email" verifies email format
        // Entity attribute: @Column(unique=true) verifies that the email is unique.

        // Entity attribute: @Column(unique=true) verifies that the displayName is unique.
        // Check that displayName length is between 3 and 50
        if (adventurer.getDisplayName().length() < 3 || adventurer.getDisplayName().length() > 50) {
            throw new IllegalArgumentException("DisplayName length must be between 3 and 50 characters.");
        }

        adventurer.setPasswordHashed(passwordEncoder.encode(adventurer.getPasswordHashed()));

        adventurer.setDateCreated(new Date(System.currentTimeMillis()));

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

    @Override
    public List<Adventurer> findAll() {
        return repository.findAll();
    }

    @Override
    public Adventurer signIn(Adventurer adventurer) {
        Optional<Adventurer> exists = findByEmail(adventurer.getEmail());

        if(exists.isPresent()) {
            // Adventurer with this email exists, compare the password to the hashed one
            if( passwordEncoder.matches(adventurer.getPasswordHashed(), exists.get().getPasswordHashed())){
                return exists.get();
            }
            // Else can throw error to show that email was correct, but username was wrong.
        }
        // Can throw error to show that email was wrong, or display error in different manner.
        return null;
    }



}
