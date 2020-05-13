package org.danslarue.homeless.services.interfaces;

import org.danslarue.homeless.models.Shelter;

import java.util.List;

public interface IShelterService {

    Shelter findByDestination(String countryName);

    List<Shelter> allShelter();

    Shelter saveShelter(Shelter shelter);
}
