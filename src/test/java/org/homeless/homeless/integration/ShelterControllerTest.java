package org.homeless.homeless.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShelterControllerTest {

    @Autowired
    private WebTestClient webClient;

    /**
     * create a shelter with GraphQL
     */

    /**
     * try to create a shelter with GraphQL but get an error
     */

    /**
     * find a shelter by destination using graphql
     */
}
