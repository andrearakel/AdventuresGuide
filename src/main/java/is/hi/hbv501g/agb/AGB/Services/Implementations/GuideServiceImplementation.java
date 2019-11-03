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

@Service
public class GuideServiceImplementation implements GuideService {

    @Autowired
    GuideRepository repository;

    @Autowired
    public GuideServiceImplementation(GuideRepository guideRepository) {
        this.repository = guideRepository;
    }

    @Override
    public Guide save(Guide guide) {
        return repository.save(guide);
    }

    @Override
    public void delete(Guide guide) {
        repository.delete(guide);
    }

    @Override
    public List<Guide> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Guide> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Guide> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public List<Guide> findByTemplate(EnumSet template) { return repository.findByTemplate(template); }

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
