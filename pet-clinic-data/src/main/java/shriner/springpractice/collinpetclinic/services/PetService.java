package shriner.springpractice.collinpetclinic.services;

import shriner.springpractice.collinpetclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
