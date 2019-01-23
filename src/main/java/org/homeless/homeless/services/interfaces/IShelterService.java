package org.homeless.homeless.services.interfaces;

import org.homeless.homeless.models.Shelter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IShelterService {
    
    Mono<Shelter> findByDestination(String countryName);

    Flux<Shelter> allShelter();

    Flux<Shelter> saveShelter(Mono<Shelter> shelter);

//    void fillDbWithDumbData();
}
