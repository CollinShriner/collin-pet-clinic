package shriner.springpractice.collinpetclinic.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import shriner.springpractice.collinpetclinic.model.Owner;
import shriner.springpractice.collinpetclinic.model.Pet;
import shriner.springpractice.collinpetclinic.model.PetType;
import shriner.springpractice.collinpetclinic.services.OwnerService;
import shriner.springpractice.collinpetclinic.services.PetService;
import shriner.springpractice.collinpetclinic.services.PetTypeService;
import shriner.springpractice.collinpetclinic.services.VisitService;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes()
    {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId)
    {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model) {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
        if (pet.getName() != null && StringUtils.length(pet.getName()) != 0 && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        if (result.hasErrors()) {
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            pet.setOwner(owner);
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            //owner.getPets().add(pet);
            pet.setOwner(owner);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

}
