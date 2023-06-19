package shriner.springpractice.collinpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import shriner.springpractice.collinpetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
