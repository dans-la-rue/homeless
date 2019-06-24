package org.homeless.homeless.repositories;

import org.homeless.homeless.models.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, String> {

//    Flux<Shelter> findAll();

    Shelter findFirstByAddress(String address);

//    /**
//     * 
//     * Additional custom finder method, backed by a View that indexes
//     * the names.
//     */
//    @View(designDocument = "user", viewName = "byName")
//    List<Shelter> findByLastname(String lastName);

//    /**
//     * Additional custom finder method, backed by a geospatial view and
//     * allowing multi-dimensional queries.
//     * You can also query within a Circle or a Polygon.
//     */
//    @Dimensional(designDocument = "userGeo", spatialViewName = "byLocation")
//    List<Shelter> findByLocationWithin();
}
