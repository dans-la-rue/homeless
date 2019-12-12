package org.danslarue.homeless.controllers;

import lombok.extern.slf4j.Slf4j;
import org.danslarue.homeless.exceptions.ResourceNotFoundException;
import org.danslarue.homeless.models.Shelter;
import org.danslarue.homeless.repositories.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
