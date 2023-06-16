package shriner.springpractice.collinpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shriner.springpractice.collinpetclinic.model.Owner;
import shriner.springpractice.collinpetclinic.model.Pet;
import shriner.springpractice.collinpetclinic.model.PetType;
import shriner.springpractice.collinpetclinic.model.Vet;
import shriner.springpractice.collinpetclinic.services.OwnerService;
import shriner.springpractice.collinpetclinic.services.PetTypeService;
import shriner.springpractice.collinpetclinic.services.VetService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Collin");
        owner1.setLastName("Shriner");
        owner1.setAddress("123 Street");
        owner1.setCity("City");
        owner1.setTelephone("5555555555");

        Pet collinPet = new Pet();
        collinPet.setPetType(savedDogType);
        collinPet.setName("Lilly");
        collinPet.setOwner(owner1);
        collinPet.setBirthDate(LocalDate.of(2011, 4, 1));
        owner1.getPets().add(collinPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Steve");
        owner2.setLastName("Mine");

        Pet stevePet = new Pet();
        stevePet.setPetType(savedCatType);
        stevePet.setName("CatName");
        stevePet.setOwner(owner2);
        stevePet.setBirthDate(LocalDate.of(2020, 5, 3));
        owner1.getPets().add(stevePet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");


        Vet vet1 = new Vet();
        vet1.setFirstName("(Vet) First");
        vet1.setLastName("(Vet) Last");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("(Vet) Unique");
        vet2.setLastName("(Vet) Name");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");

    }
}
