package org.homeless.homeless.services.implementation;

import org.homeless.homeless.models.Shelter;
import org.homeless.homeless.repositories.ShelterRepository;
import org.homeless.homeless.services.interfaces.IShelterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ShelterServiceImpl implements IShelterService {

    private final ShelterRepository shelterRepository;

    @Autowired
    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @Override
    public Mono<Shelter> findByDestination(String destination) {
//        shelterRepository.findAll().subscribe(shelter -> {log.error(shelter.toString());});
        //TODO : business checks before insert
        return shelterRepository.findFirstByDestination(destination)
                .map(it -> {
                    log.debug(it.toString());
                    return it;
                });
    }

    /**
     * we should never do this kind of requests
     * @return Flux of all Shelters
     */
    @Override
    public Flux<Shelter> allShelter() {
        // http://javasampleapproach.com/reactive-programming/reactor/reactor-convert-flux-into-list-map-reactive-programming
        return Flux.fromIterable(this.shelterRepository.findAll());
    }

    /**
     * Just for debugging purpose
     * need to be removed and replaced by integration tests
     */
/*    public void fillDbWithDumbData() {
        Flux<Shelter> flux = Flux.just(
                new Shelter("Jack", "afghanistan"),
                new Shelter("Chloe", "afghanistan"),
                new Shelter("afghanistan", "afghanistan"),
                new Shelter("David", "afghanistan"),
                new Shelter("Michelle", "afghanistan"));
        shelterRepository
                .saveAll(flux)
                .subscribe(shelter -> {log.error(shelter.toString());});
    }*/

    @Override
    public Flux<Shelter> saveShelter(Mono<Shelter> shelter) {
        return null;
//        return shelterRepository.saveAll(shelter);
    }
}