package org.danslarue.homeless.integration;

import lombok.extern.slf4j.Slf4j;
import org.danslarue.homeless.models.Shelter;
import org.danslarue.homeless.repositories.IShelterRepository;
import org.danslarue.homeless.services.interfaces.IShelterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ShelterControllerTest {

    @MockBean
    private IShelterService shelterService;

    @MockBean
    private IShelterRepository shelterRepository;

    @Autowired
    private MockMvc mvc;

    @Test
    // TODO check the business rules instead
    public void gotAllShelter() throws Exception {
        List<Shelter> shelterList = new ArrayList<>();
        Shelter myFirstShelter = new Shelter();
        myFirstShelter.setAddress("toto");
        shelterList.add(myFirstShelter);
        Mockito.when(shelterService.allShelter()).thenReturn(shelterList);
        Page<Shelter> page = new PageImpl<Shelter>(shelterList);
        Mockito.when(shelterRepository.findAll(any(Pageable.class))).thenReturn(page);
        shelterList = shelterService.allShelter();
        assertNotNull(shelterList);

        mvc.perform(get("/api/v1/shelters")
                .header("Authorization", "Basic " + "YWRtaW46bmltZGE=")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].address", is(myFirstShelter.getAddress())));
    }

    /**
     * create a shelter
     */

    /**
     * try to create a shelter but get an error
     */

    /**
     * find a shelter by destination 
     */

    /**
     * update a shelter
     *
     */

    /**
     * delete a shelter
     *
     */
}
