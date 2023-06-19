package shriner.springpractice.collinpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import shriner.springpractice.collinpetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
