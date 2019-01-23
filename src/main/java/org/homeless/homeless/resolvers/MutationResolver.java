package com.dakar.dakar.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.dakar.dakar.models.Shelter;
import com.dakar.dakar.models.ShelterInput;
import com.dakar.dakar.services.interfaces.IShelterService;
import reactor.core.publisher.Mono;

public class MutationResolver implements GraphQLMutationResolver {

    // no autowired here
    private IShelterService ShelterService;

    //instead we use the old fashion
    public MutationResolver(IShelterService shelterService) {
        this.shelterService = shelterService;
    }

    public Shelter createShelter(ShelterInput ShelterInput) {
        Shelter shelterCreated = new Shelter(null, shelterInput.getPrice(), shelterInput.getDestination(), "");
        Mono<Shelter> shelterMono = Mono.just(shelterCreated);
        return shelterService.saveShelter(shelterMono).blockFirst();
    }

}
