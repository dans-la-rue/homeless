package org.danslarue.homeless.acceptance.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.danslarue.homeless.models.Shelter;
import org.danslarue.homeless.services.interfaces.IShelterService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaveAShelterSteps {

    private Shelter shelter = new Shelter();

    @Autowired
    private IShelterService shelterService;

    @Given("^(.*) participated to the creation of one shelter$")
    public void user_participated_to_the_creation_of_one_shelter(String userName) {
        shelter = new Shelter();
        shelter.setAvailableBeds(3);
        shelter.setAddress(userName);
        //TODO, you should not be able to save a shelter with a field set to null and annotated @NonNull
        // price has the annotation @NonNull but we still can save it in couchbase
        shelterService.saveShelter(shelter);
    }

    @Then("^The shelter (.*) is created with (\\d+) as address$")
    public void shelter_is_created(String shelterExpected, Integer beds) {
        assertNotNull(beds);
        assertNotNull(shelterExpected);
        assertEquals(shelterExpected, shelter.getAddress());
        assertEquals(beds, shelter.getAvailableBeds());
    }

    @Then("^(.*) gets all his shelters$")
    public void user_gets_his_shelter(String shelterExpected) {
        assertEquals(shelterExpected, shelter.getAddress());
    }

    @When("(.*) creates the shelter: (.*)")
    public void usernameCreatesTheShelterDestination(String a, String b) {
        user_participated_to_the_creation_of_one_shelter("dreaser");
    }

    @When("(.*) search his shelters")
    public void usernameSearchHisShelters(String s) {
    }
}
