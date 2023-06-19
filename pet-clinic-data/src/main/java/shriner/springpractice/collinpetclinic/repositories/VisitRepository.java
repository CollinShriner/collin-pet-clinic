package shriner.springpractice.collinpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import shriner.springpractice.collinpetclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
