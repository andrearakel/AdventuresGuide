package is.hi.hbv501g.agb.AGB.Services.Implementations;

import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Repositories.GuideRepository;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

/**
 * Programmers:
 * id   name            email
 * jgs  Jónas G.        jgs7@hi.is
 *
 *
 * Changes:
 * no.  idProg  date    description
 * 1.   jgs     031119  Connecting save, delete and findAll to GuideRepository
 * 2.   jgs     031119  createGuide method
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

        return save(guide);
    }


    @Override
    public Optional<Guide> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Guide> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    //@Override
    //public List<Guide> findByTemplate(EnumSet templates) { return repository.findByTemplate(templates); }

    @Override
    public List<Guide> findByLocation(String location) { return repository.findByLocation(location); }

    @Override
    public List<Guide> findByMatches(Guide guide) {

        //New list made, all guides with title match added to the list.
        ArrayList<Guide> gList, allList;
        gList = (ArrayList<Guide>) repository.findByTitle(guide.getTitle());

        //Guides that don't match the country taken out
        allList = (ArrayList<Guide>) repository.findByLocation(guide.getCountry());
        gList.retainAll(allList);

        //Guides that don't match the template taken out
        //No idea how to do that...

        return gList;


    }
}
