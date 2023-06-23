package shriner.springpractice.collinpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shriner.springpractice.collinpetclinic.model.*;
import shriner.springpractice.collinpetclinic.services.*;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        count+=vetService.findAll().size();
        count+=ownerService.findAll().size();
        count+=specialityService.findAll().size();

        if(count==0)
        {
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");

        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");

        Speciality savedSurgery=specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");

        Speciality savedDentistry=specialityService.save(dentistry);


        Owner owner1 = new Owner();
        owner1.setFirstName("Ping");
        owner1.setLastName("Pong");
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
        owner2.setAddress("Craft Street");
        owner2.setCity("Block");
        owner2.setTelephone("5552225555");

        Pet stevePet = new Pet();
        stevePet.setPetType(savedCatType);
        stevePet.setName("CatName");
        stevePet.setOwner(owner2);
        stevePet.setBirthDate(LocalDate.of(2020, 5, 3));
        owner2.getPets().add(stevePet);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(stevePet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Cat Cold");

        visitService.save(catVisit);
        System.out.println("Loaded Owners...");


        Vet vet1 = new Vet();
        vet1.setFirstName("(Vet) First");
        vet1.setLastName("(Vet) Last");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("(Vet) Unique");
        vet2.setLastName("(Vet) Name");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
