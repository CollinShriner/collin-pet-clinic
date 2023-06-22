package shriner.springpractice.collinpetclinic.services.map;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shriner.springpractice.collinpetclinic.model.Owner;

import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String ownerLastName = "Last";

    @BeforeEach
    void setUp() {

        ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());

        ownerMapService.save(Owner.builder().id(ownerId).lastName(ownerLastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        then(ownerSet.size()).isEqualTo(1);
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        then(owner.getId()).isEqualTo(ownerId);
    }

    @Test
    void save() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();

        Owner savedOwner = ownerMapService.save(owner2);

        then(savedOwner.getId()).isEqualTo(id);
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        then(savedOwner).isNotNull();
        then(savedOwner.getId()).isNotNull();
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        then(ownerMapService.findAll().size()).isEqualTo(0);
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        then(ownerMapService.findAll().size()).isEqualTo(0);
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(ownerLastName);

        then(owner).isNotNull();
        then(owner.getId()).isEqualTo(ownerId);
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerMapService.findByLastName("NotAName");

        then(owner).isNull();
    }
}