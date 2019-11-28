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
 * 3    eok     020119  Renamed save(..) to signUp(..), added default save(..) instead.
 *                      Implemented updateProfile(..)
 * 4    eok     261119  Added comments and removed unused functions.
 */

/**
 * Invokes methods from the AdventurerRepository.
 * Takes care of business logic and functionality which is not provided by the repository.
 */
@Service // Service is an extra layer between controller and repository(database) which can do more than the Repository itself.
public class AdventurerServiceImplementation implements AdventurerService {

    AdventurerRepository repository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Constructor
    @Autowired
    public AdventurerServiceImplementation(AdventurerRepository adventurerRepository){ this.repository = adventurerRepository; }

    @Override
    public Adventurer save(Adventurer adventurer) {
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
    public Optional<Adventurer> findById(long id) {
        return repository.findById(id);
    }

    /**
     * Verifies the user input, hashes the password, sets dateCreated and invokes a method to save the new adventurer to the database.
     * @param adventurer
     * @return the adventurer, having been saved to the database.
     */
    @Override
    public Adventurer signUp(Adventurer adventurer) {

        // HTML id="email" verifies email format
        // Entity attribute: @Column(unique=true) verifies that the email is unique.
        // Entity attribute: @Column(unique=true) verifies that the displayName is unique.
        // Entity attribute: @Size(..) verifies lengths of displayName, email and password.

        adventurer.setPasswordHashed(passwordEncoder.encode(adventurer.getPasswordHashed()));

        adventurer.setDateCreated(new Date(System.currentTimeMillis()));

        return save(adventurer);
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

    /**
     * Copies the attribute values that can be edited on the editprofile page from the adventurer to the sessionAdventurer.
     * @param adventurer
     * @param sessionAdventurer
     * @return The sessionAdventurer updated with the values from the adventurer.
     */
    @Override
    public Adventurer updateProfile(Adventurer adventurer, Adventurer sessionAdventurer) {
        // TODO: validate input.
        sessionAdventurer.setName(adventurer.getName());
        sessionAdventurer.setBiography(adventurer.getBiography());
        sessionAdventurer.setDateOfBirth(adventurer.getDateOfBirth());
        sessionAdventurer.setCity(adventurer.getCity());
        sessionAdventurer.setState(adventurer.getState());
        sessionAdventurer.setCountry(adventurer.getCountry());

        return save(sessionAdventurer);
    }



}
