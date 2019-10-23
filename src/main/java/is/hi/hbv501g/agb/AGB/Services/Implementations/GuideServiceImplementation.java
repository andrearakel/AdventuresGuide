package is.hi.hbv501g.agb.AGB.Services.Implementations;

import is.hi.hbv501g.agb.AGB.Entities.Guide;
import is.hi.hbv501g.agb.AGB.Repositories.GuideRepository;
import is.hi.hbv501g.agb.AGB.Services.Interfaces.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuideServiceImplementation implements GuideService {

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



}
