package com.dakar.dakar.services.interfaces;

import com.dakar.dakar.models.Shelter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IShelterService {
    Mono<Shelter> findByDestination(String countryName);

    Flux<Shelter> allShelter();

    Flux<Shelter> saveShelter(Mono<Shelter> shelter);

//    void fillDbWithDumbData();
}
