package is.hi.hbv501g.agb.AGB.Services.Implementations;

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
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1    jgs     031119  Connecting save, delete and findAll to GuideRepository
 * 2    jgs     031119  createGuide method
 * 3    eok     221119  Added default template
 */

@Service // Service is an extra layer between controller and repository(database) which can do more than the Repository itself.
public class GuideServiceImplementation implements GuideService {

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

    /**
     * Verifies the user input, sets dateCreated and invokes a method
     * to save the new guide to the database
     * @param guide
     * @return the guide, having been saved to the database
     */
    @Override
    public Guide createGuide(Guide guide) {

        /**guide.setDateCreated(new Date(System.currentTimeMillis()));**/

        if (guide.templates == null) {
            guide.templates = new HashSet<Template>();
            guide.templates.add(Template.NONE);
        }

        return save(guide);
    }


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
