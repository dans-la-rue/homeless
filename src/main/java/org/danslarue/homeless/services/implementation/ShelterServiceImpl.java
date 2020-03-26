package org.danslarue.homeless.services.implementation;

import lombok.extern.slf4j.Slf4j;
import org.danslarue.homeless.models.Shelter;
import org.danslarue.homeless.repositories.IShelterRepository;
import org.danslarue.homeless.services.interfaces.IShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ShelterServiceImpl implements IShelterService {

    @Autowired
    private IShelterRepository shelterRepository;

    @Override
    public Shelter findByDestination(String address) {
        //TODO : business checks before insert
        return shelterRepository.findFirstByAddress(address);
    }

    /**
     * We should never do this kind of requests
     * 
     * @return List of all Shelters
     */
    @Override
    public List<Shelter> allShelter() {
        // http://javasampleapproach.com/reactive-programming/reactor/reactor-convert-flux-into-list-map-reactive-programming
        return this.shelterRepository.findAll();
    }

    @Override
    public Shelter saveShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }
}
