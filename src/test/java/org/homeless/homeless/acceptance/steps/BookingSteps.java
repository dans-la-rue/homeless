package org.homeless.homeless.acceptance.steps;

import org.homeless.homeless.models.GraphQLParameter;
import org.homeless.homeless.models.Shelter;
import org.homeless.homeless.models.SimpleExecutionResult;
import org.homeless.homeless.services.interfaces.IShelterService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;

@Slf4j
//The spring runner have to be setup only once for cucumber tests
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingSteps {

	private Shelter shelterFetched;

	@Autowired
	private IShelterService shelterService;

	@Autowired
	private WebTestClient webClient;

	@Given("^These shelters have been created$")
	public void user_wants_to_go_to_a_shelter() {
		Shelter shelter = new Shelter();
		shelter.setDestination("Vietnam");
		shelter.setOwner("Dakar");
		shelterService.saveShelter(Mono.just(shelter));
	}

	@When("^(.*) search a destination: (.*)")
	public void user_chose_a_destination_Vietnam(String userName, String destination) {
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
				.consumeWith(executionResult -> {
					executionResult.getResponseBody().getData();
					shelterFetched = new Shelter();
				});
	}

	@Then("^(.*) find (.*) destinations matching (.*)$")
	public void shelter_is_created(String userNAme, String nbrMatches, String shelterExpected) {
		assertEquals(shelterExpected, shelterFetched.getDestination());
	}
}
