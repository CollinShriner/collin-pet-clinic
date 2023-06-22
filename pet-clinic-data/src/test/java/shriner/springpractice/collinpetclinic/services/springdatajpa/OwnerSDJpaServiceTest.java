package shriner.springpractice.collinpetclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shriner.springpractice.collinpetclinic.model.Owner;
import shriner.springpractice.collinpetclinic.repositories.OwnerRepository;
import shriner.springpractice.collinpetclinic.repositories.PetRepository;
import shriner.springpractice.collinpetclinic.repositories.PetTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Last";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner returnOwner;

    @BeforeEach
    void setup(){
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(Set.of(returnOwner));
        Set<Owner> owners = service.findAll();

        then(owners.size()).isEqualTo(1);
        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(returnOwner));
        Owner owner = service.findById(returnOwner.getId());

        then(owner).isEqualTo(returnOwner);
        verify(ownerRepository).findById(anyLong());
    }

    @Test
    void save() {
        Owner saveOwner = Owner.builder().id(2L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner savedOwner = service.save(saveOwner);

        then(savedOwner).isNotNull();

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
        Owner owner = service.findByLastName(LAST_NAME);

        then(owner.getLastName()).isEqualTo(LAST_NAME);
        verify(ownerRepository).findByLastName(any());
    }
}