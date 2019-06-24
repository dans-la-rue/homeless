package org.homeless.homeless.acceptance.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.homeless.homeless.models.Shelter;
import org.homeless.homeless.services.interfaces.IShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

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
        shelter.setAvailableBeds(3);
        shelter.setAddress(userName);
        //TODO, you should not be able to save a shelter with a field set to null and annotated @NonNull
        // price has the annotation @NonNull but we still can save it in couchbase
        shelterService.saveShelter(shelter);
    }

    @Then("^The shelter (.*) is created with (\\d+) as owner$")
    public void shelter_is_created(String shelterExpected, Integer beds) {
        assertEquals(shelterExpected, shelter.getAddress());
        assertEquals(beds, shelter.getAvailableBeds());
    }

    @Then("^(.*) gets all his shelters$")
    public void user_gets_his_shelter(String shelterExpected) {
        assertEquals(shelterExpected, shelter.getAddress());
    }
}
