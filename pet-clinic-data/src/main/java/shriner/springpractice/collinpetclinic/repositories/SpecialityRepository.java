package shriner.springpractice.collinpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import shriner.springpractice.collinpetclinic.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
