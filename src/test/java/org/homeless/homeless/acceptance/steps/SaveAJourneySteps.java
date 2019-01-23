package com.dakar.dakar.acceptance.steps;

import com.dakar.dakar.models.GraphQLParameter;
import com.dakar.dakar.models.Shelter;
import com.dakar.dakar.models.SimpleExecutionResult;
import com.dakar.dakar.services.interfaces.IShelterService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;

@Slf4j
public class SaveAShelterSteps {

    private Shelter shelter;

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private IShelterService shelterService;

    @Given("^(.*) participated to the creation of one shelter$")
    public void user_participated_to_the_creation_of_one_shelter(String userName) {
        Shelter shelter = new Shelter();
        shelter.setDestination(userName);
        shelter.setOwner(userName);
        //TODO, you should not be able to save a shelter with a field set to null and annotated @NonNull
        // price has the annotation @NonNull but we still can save it in couchbase
        shelterService.saveShelter(Mono.just(shelter));
    }

    @When("^(.*) search his shelters$")
    public void user_search_his_shelter(String userName) {
        GraphQLParameter graphQLParameter = new GraphQLParameter();
//		TODO put the correct query here 
        graphQLParameter.setQuery("{allShelter {destination\nprice}}");
        this.webClient.post().uri("/graphql")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(graphQLParameter))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(SimpleExecutionResult.class)
                .consumeWith(shelter -> {
                    shelter.getResponseBody().getData();
                    this.shelter = new Shelter();
                    this.shelter.setDestination("Dakar");//TODO fetch from the returned data (parse the JSON)
                });
    }

    @When("^(.*) create the shelter for destination: (.*)$")
    public void user_wants_to_go_to_a_shelter(String userName, String destination) {
        String query = " mutation {\n" +
                "            createShelter(input:{ owner:\"" + userName + "\", destination:\"" + destination + "\" }){\n" +
                "                owner\n" +
                "                destination\n" +
                "            }\n" +
                "}\n";
        GraphQLParameter graphQLParameter = new GraphQLParameter();
        graphQLParameter.setQuery(query);
        this.webClient.post().uri("/graphql")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(graphQLParameter))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(SimpleExecutionResult.class)
                .consumeWith(simpleExecutionResult -> {
                    Assert.assertTrue(simpleExecutionResult.getResponseBody().getData().toString().contains(destination));
                    simpleExecutionResult.getResponseBody().getData();
                    this.shelter = new Shelter();
                    this.shelter.setDestination(destination);//TODO fetch from the returned data
                    this.shelter.setPrice(destination);
                    this.shelter.setOwner(userName);
                });
    }

    @Then("^The shelter (.*) is created with (.*) as owner$")
    public void shelter_is_created(String shelterExpected, String owner) {
        assertEquals(shelterExpected, shelter.getDestination());
        assertEquals(owner, shelter.getOwner());
    }

    @Then("^(.*) gets all his shelters$")
    public void user_gets_his_shelter(String shelterExpected) {
        assertEquals(shelterExpected, shelter.getDestination());
    }
}
