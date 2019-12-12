package org.danslarue.homeless.unit;

import lombok.extern.slf4j.Slf4j;
import org.danslarue.homeless.models.Shelter;
import org.danslarue.homeless.repositories.ShelterRepository;
import org.danslarue.homeless.services.implementation.ShelterServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@Slf4j
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DakarApplicationUnitTest {

    @InjectMocks
    private ShelterServiceImpl shelterService;

    @Mock
    private ShelterRepository shelterRepository;

    @Test
    // stupid tests
    // check the business rules instead of just the values
    public void gotAllShelter() {
        List<Shelter> shelterList = new ArrayList<>();
        shelterList.add(new Shelter());
        Mockito.when(shelterRepository.findAll()).thenReturn(shelterList);

        shelterList = shelterService.allShelter();
        assertNotNull(shelterList);
    }

    @Test
    public void insertShelter() {
        Shelter shelter = new Shelter();
        Mockito.when(shelterRepository.save(any(Shelter.class))).thenReturn(new Shelter());

        Shelter shelterInserted = shelterService.saveShelter(shelter);
        assertNotNull(shelterInserted);
    }
}
