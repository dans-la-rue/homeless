package org.homeless.homeless.controllers;

import lombok.extern.slf4j.Slf4j;
import org.homeless.homeless.exceptions.MonException;
import org.homeless.homeless.exceptions.ResourceNotFoundException;
import org.homeless.homeless.models.Shelter;
import org.homeless.homeless.repositories.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ShelterController {

    @Autowired
    private ShelterRepository shelterRepository;

    @GetMapping("/shelters")
    public Page<Shelter> getShelters(Pageable pageable) {
        log.info("get shelters");
        // examples of things I can throw and that will be catch by the controller advice
//        throw new ResourceNotFoundException("yop");
//        throw new MonException("yop");
        return shelterRepository.findAll(pageable);
    }

    @PostMapping("/shelters")
    public Shelter createShelter(@Valid @RequestBody Shelter shelter) {
        log.info("post shelters");
        return shelterRepository.save(shelter);
    }

    @PutMapping("/shelters/{shelterId}")
    public Shelter updateShelter(@PathVariable Integer shelterId,
                                 @Valid @RequestBody Shelter shelterRequest) {
        log.info("put shelters");
        return shelterRepository.findById(shelterId)
                .map(shelter -> {
                    shelter.setAddress(shelterRequest.getAddress());
                    shelter.setAvailableBeds(shelterRequest.getAvailableBeds());
                    return shelterRepository.save(shelter);
                }).orElseThrow(() -> new ResourceNotFoundException("Shelter not found with id " + shelterId));
    }


    @DeleteMapping("/shelters/{shelterId}")
    public ResponseEntity<?> deleteShelter(@PathVariable Integer shelterId) {
        log.info("delete shelters");
        return shelterRepository.findById(shelterId)
                .map(shelter -> {
                    shelterRepository.delete(shelter);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Shelter not found with id " + shelterId));
    }
}
