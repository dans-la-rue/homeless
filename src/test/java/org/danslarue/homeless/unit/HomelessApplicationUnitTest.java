package org.danslarue.homeless.unit;

import lombok.extern.slf4j.Slf4j;
import org.danslarue.homeless.models.Shelter;
import org.danslarue.homeless.repositories.IShelterRepository;
import org.danslarue.homeless.services.interfaces.IShelterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
//@TestConfiguration
public class HomelessApplicationUnitTest {

    @Autowired
    IShelterService shelterService;

    @MockBean
    IShelterRepository shelterRepository;

    @Test
    // TODO check the business rules instead
    public void gotAllShelter() {
        List<Shelter> shelterList = new ArrayList<>();
        shelterList.add(new Shelter());
        when(shelterRepository.findAll()).thenReturn(shelterList);

        shelterList = shelterService.allShelter();
        assertNotNull(shelterList);
    }
}
