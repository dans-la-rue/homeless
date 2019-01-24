package org.homeless.homeless.unit;

import org.homeless.homeless.models.Shelter;
import org.homeless.homeless.repositories.ShelterRepository;
import org.homeless.homeless.services.implementation.ShelterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DakarApplicationUnitTests {

    @InjectMocks
    private ShelterServiceImpl shelterService;

    @Mock
    private ShelterRepository shelterRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    //stupid test
    public void gotAllShelter() {
//        when(shelterRepository.findAll()).thenReturn(Flux.just(new Shelter()));

        List<Shelter> shelterList = shelterService.allShelter().collectList().block();
        assertNotNull(shelterList);
    }

    @Test
    public void insertShelter() {
        Shelter shelter = new Shelter();
        Mono<Shelter> shelterMono = Mono.just(shelter);

//        when(shelterRepository.saveAll((Publisher<Shelter>) any())).thenReturn(Flux.just(shelter));

        Flux<Shelter> shelterInserted = shelterService.saveShelter(shelterMono);
        //TODO : check the business rules instead of just the values
        assertNotNull(shelterInserted);
    }
}
