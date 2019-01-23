package com.dakar.dakar.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.dakar.dakar.models.Shelter;
import com.dakar.dakar.services.interfaces.IShelterService;

import java.util.List;

/**
 *
 * We can't use autowired here with this https://github.com/graphql-java/graphql-spring-boot because
 * it does not support webflux yet
 * and https://github.com/oembedler/spring-graphql-common#requires
 * is outdated
 */
public class QueryResolver implements GraphQLQueryResolver {

    // no autowire possible here yet
    private IShelterService shelterService;

    //instead we use the old fashion
    public QueryResolver(IShelterService shelterService) {
        this.shelterService = shelterService;
    }

    /**
     * Here we'll gonna call our proper service, where we can use Autowired as we want
     * @return see if we can return a Flux there ?
     * 
     * for the Flux we will wait for this : https://github.com/graphql-java/graphql-java-tools/issues/103
     */
    public List<Shelter> allShelter() {
        return shelterService.allShelter().collectList().block();
    }
}