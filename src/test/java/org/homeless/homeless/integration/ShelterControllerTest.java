package org.homeless.homeless.integration;

import lombok.extern.slf4j.Slf4j;
import org.homeless.homeless.models.Shelter;
import org.homeless.homeless.repositories.ShelterRepository;
import org.homeless.homeless.services.implementation.ShelterServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShelterControllerTest {

    // https://www.mkyong.com/spring-boot/spring-rest-integration-test-example/
    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * create a shelter 
     */

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

        shelterList = shelterService.allShelter();
        assertNotNull(shelterList);
    }

    /**
     * try to create a shelter but get an error
     */

    /**
     * find a shelter by destination 
     */
}
