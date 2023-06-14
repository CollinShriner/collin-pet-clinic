package shriner.springpractice.collinpetclinic.services;

import shriner.springpractice.collinpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
