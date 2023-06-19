package shriner.springpractice.collinpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import shriner.springpractice.collinpetclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
