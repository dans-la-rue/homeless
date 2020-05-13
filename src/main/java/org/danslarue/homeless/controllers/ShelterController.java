package org.danslarue.homeless.controllers;

import lombok.extern.slf4j.Slf4j;
import org.danslarue.homeless.dto.ShelterDTO;
import org.danslarue.homeless.exceptions.ResourceNotFoundException;
import org.danslarue.homeless.models.Shelter;
import org.danslarue.homeless.repositories.IShelterRepository;
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
    private IShelterRepository shelterRepository;

    @GetMapping("/shelters")
    public Page<Shelter> getShelters(Pageable pageable) {
        log.info("get shelters");
        // examples of things I can throw and that will be catch by the controller advice
//        throw new ResourceNotFoundException("yop");
//        throw new MonException("yop");
        return shelterRepository.findAll(pageable);
    }

    @PostMapping("/shelters")
    public Shelter createShelter(@Valid @RequestBody ShelterDTO shelter) {
        log.info("post shelters");
        Shelter persistedShelter = new Shelter(shelter);
        return shelterRepository.save(persistedShelter);
    }

    @PutMapping("/shelters/{shelterId}")
    public Shelter updateShelter(@PathVariable Integer shelterId,
                                 @Valid @RequestBody ShelterDTO shelterRequest) {
        log.info("put shelters");
        // TODO: This may be done directly without fetching the old version
        return shelterRepository.findById(shelterId)
                .map(persistedShelter -> {
                    persistedShelter.setAddress(shelterRequest.getAddress());
                    persistedShelter.setNews(shelterRequest.getNews());
                    persistedShelter.setAvailableBeds(shelterRequest.getAvailableBeds());
                    return shelterRepository.save(persistedShelter);
                }).orElseThrow(() -> new ResourceNotFoundException("Shelter not found with id " + shelterId));
    }

    @DeleteMapping("/shelters/{shelterId}")
    public ResponseEntity<?> deleteShelter(@PathVariable Integer shelterId) {
        log.info("delete shelters");
        return shelterRepository.findById(shelterId)
                .map(persistedShelter -> {
                    shelterRepository.delete(persistedShelter);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Shelter not found with id " + shelterId));
    }
}
