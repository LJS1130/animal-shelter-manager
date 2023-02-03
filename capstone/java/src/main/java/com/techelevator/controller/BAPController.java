package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class BAPController {

    private PetDao petDao;

    public BAPController(PetDao petDao) {
        this.petDao = petDao;
    }

    @GetMapping("/pets")
    public List<Pet> getAllPets() throws InterruptedException {
        Thread.sleep(1000);
        return petDao.getAllPets();
    }

    @GetMapping("/pets/{id}")
    public Pet findTransaction(@PathVariable int id) {
        try {
            return petDao.getPet(id);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet was not found");
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','VOLUNTEER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/pets")
    public void addNewPet(@Valid @RequestBody Pet pet) {
        petDao.submitPet(pet);
    }

    @PreAuthorize("hasAnyRole('ADMIN','VOLUNTEER')")
    @PutMapping("/pets/{id}/{isAvailable}")
    public void changePetAvailability(@PathVariable int id, @PathVariable boolean isAvailable) {
        petDao.updateAvailability(id, isAvailable);
    }

    @PreAuthorize("hasAnyRole('ADMIN','VOLUNTEER')")
    @PutMapping("/pets/{id}")
    public void updatePet(@PathVariable int id, @Valid @RequestBody Pet pet) {
        petDao.updatePet(pet, id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','VOLUNTEER')")
    @DeleteMapping("/pets/{id}")
    public void deletePet(@PathVariable int id) {
        petDao.deletePet(id);
    }


}
