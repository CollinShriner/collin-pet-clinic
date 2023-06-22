package shriner.springpractice.collinpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import shriner.springpractice.collinpetclinic.model.Owner;
import shriner.springpractice.collinpetclinic.model.Pet;
import shriner.springpractice.collinpetclinic.services.OwnerService;
import shriner.springpractice.collinpetclinic.services.PetService;
import shriner.springpractice.collinpetclinic.services.PetTypeService;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerMapService(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {

        if(object != null){
            if(object.getPets() != null)
            {
                object.getPets().forEach(pet ->
                {
                    if(pet.getPetType()!=null)
                    {
                        pet.setPetType(petTypeService.save(pet.getPetType()));
                    }
                    else{
                        throw new RuntimeException("Pet Type is required");
                    }
                    if(pet.getId() == null)
                    {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        }
        else{
            return null;
        }

    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return super.findAll()
                .stream()
                .filter(owner -> owner.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }
}
