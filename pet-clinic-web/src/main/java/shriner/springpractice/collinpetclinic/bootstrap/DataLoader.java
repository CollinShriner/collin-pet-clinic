package shriner.springpractice.collinpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shriner.springpractice.collinpetclinic.model.Owner;
import shriner.springpractice.collinpetclinic.model.PetType;
import shriner.springpractice.collinpetclinic.model.Vet;
import shriner.springpractice.collinpetclinic.services.OwnerService;
import shriner.springpractice.collinpetclinic.services.PetTypeService;
import shriner.springpractice.collinpetclinic.services.VetService;

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
        petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Collin");
        owner1.setLastName("Shriner");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Steve");
        owner2.setLastName("Mine");

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
