package org.homeless.homeless.services.interfaces;

import org.homeless.homeless.models.Shelter;

import java.util.List;

public interface IShelterService {

    Shelter findByDestination(String countryName);

    List<Shelter> allShelter();

    Shelter saveShelter(Shelter shelter);

//    void fillDbWithDumbData();
}
