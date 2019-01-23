package com.dakar.dakar.repositories;

import com.dakar.dakar.models.Shelter;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ShelterRepository extends {

    Flux<Shelter> findAll();

    Mono<Shelter> findFirstByDestination(String destination);

    /**
     * Additional custom finder method, backed by a View that indexes
     * the names.
     */
//    @View(designDocument = "user", viewName = "byName")
//    List<Shelter> findByLastname(String lastName);

    /**
     * Additional custom finder method, backed by a geospatial view and
     * allowing multi-dimensional queries.
     * You can also query within a Circle or a Polygon.
     */
//    @Dimensional(designDocument = "userGeo", spatialViewName = "byLocation")
//    List<Shelter> findByLocationWithin();
}
