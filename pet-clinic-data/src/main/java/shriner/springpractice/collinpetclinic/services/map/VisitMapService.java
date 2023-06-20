package shriner.springpractice.collinpetclinic.services.map;

import org.springframework.stereotype.Service;
import shriner.springpractice.collinpetclinic.model.Visit;
import shriner.springpractice.collinpetclinic.services.VisitService;

import java.util.Set;

@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {


    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit visit) {
        super.delete(visit);
    }

    @Override
    public Visit save(Visit visit) {
        if(visit.getPet()==null||visit.getPet().getOwner()==null)
        {
            throw new RuntimeException("Pet and Owner is required");
        }
        if(visit.getPet().getId()==null||visit.getPet().getOwner().getId()==null)
        {
            throw new RuntimeException("Pet and Owner must have ID (saved)");
        }
        return super.save(visit);
    }

    @Override
    public Visit findById(Long id) {
        return null;
    }
}
