package org.homeless.homeless.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.homeless.homeless.models.Shelter;
import org.homeless.homeless.models.ShelterInput;
import org.homeless.homeless.services.interfaces.IShelterService;
import reactor.core.publisher.Mono;

public class MutationResolver implements GraphQLMutationResolver {

    // no autowired here
    private IShelterService shelterService;

    //instead we use the old fashion
    public MutationResolver(IShelterService shelterService) {
        this.shelterService = shelterService;
    }

    public Shelter createShelter(ShelterInput shelterInput) {
        Shelter shelterCreated = new Shelter(null, shelterInput.getPrice(), shelterInput.getDestination(), "");
        Mono<Shelter> shelterMono = Mono.just(shelterCreated);
        return shelterService.saveShelter(shelterMono).blockFirst();
    }

}
