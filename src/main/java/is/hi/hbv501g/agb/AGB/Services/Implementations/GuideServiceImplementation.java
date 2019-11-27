package is.hi.hbv501g.agb.AGB.Services.Implementations;

import is.hi.hbv501g.agb.AGB.Entities.Adventurer;
import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Entities.Template;
import is.hi.hbv501g.agb.AGB.Repositories.GuideRepository;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Programmers:
 * id   name            email
 * jgs  Jónas G.        jgs7@hi.is
 * eok  Erling Oskar    eok4@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    jgs     031119  Connecting save, delete and findAll to GuideRepository
 * 2    jgs     031119  createGuide method
 * 3    eok     221119  Added default template. Added idAdventurer and dateCreated to createGuide.
 * 4    eok     261119  Added comments and removed unused functions.
 */

/**
 * Invokes methods from the GuideRepository.
 * Takes care of business logic and functionality which is not provided by the repository.
 */
@Service // Service is an extra layer between controller and repository(database) which can do more than the Repository itself.
public class GuideServiceImplementation implements GuideService {

    /**
     * Verifies the user input, sets dateCreated and invokes a method
     * to save the new guide to the database
     * @param guide
     * @return the guide, having been saved to the database
     */
    @Override
    public Guide createGuide(Guide guide, Adventurer adventurer) {

        guide.setDateCreated(new Date(System.currentTimeMillis()));
        guide.setAdventurer(adventurer);

        // Set a default template
        if (guide.getTemplates() == null) {
            Set<Template> tempTemplates = new HashSet<Template>();
            tempTemplates.add(Template.NONE);
            guide.setTemplates(tempTemplates);
        }

        return save(guide);
    }

    GuideRepository repository;

    // Constructor
    @Autowired
    public GuideServiceImplementation(GuideRepository guideRepository) { this.repository = guideRepository; }

    @Override
    public Guide save(Guide guide) { return repository.save(guide); }

    @Override
    public  void delete(Guide guide) { repository.delete(guide); }

    @Override
    public List<Guide> findAll() { return repository.findAll(); }



    @Override
    public Optional<Guide> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Guide> findByTitleContainingIgnoreCase(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Guide> findByTemplates(Set<Template> template) { return repository.findByTemplates(template); }

    @Override
    public List<Guide> findByCountryContainingIgnoreCase(String country) {
        return repository.findByCountryContainingIgnoreCase(country);
    }

    @Override
    public List<Guide> findByTitleIgnoreCaseContainingAndCountryIgnoreCaseContaining(String title, String country) {
        return repository.findByTitleIgnoreCaseContainingAndCountryIgnoreCaseContaining(title, country);
    }

}
