package org.homeless.homeless.acceptance.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.homeless.homeless.models.Shelter;
import org.homeless.homeless.services.interfaces.IShelterService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

@Slf4j
//The spring runner have to be setup only once for cucumber tests
public class BookingSteps {

	private Shelter shelterFetched;

	@Autowired
	private IShelterService shelterService;

	public BookingSteps(Shelter shelterFetched) {
		this.shelterFetched = shelterFetched;
	}

	@Given("^These shelters have been created$")
	public void user_wants_to_go_to_a_shelter() {
		Shelter shelter = new Shelter();
		shelter.setAddress("Vietnam");
		shelter.setAvailableBeds(3);
		shelterService.saveShelter(shelter);
	}

	@Then("^(.*) find (.*) destinations matching (.*)$")
	public void shelter_is_created(String userName, String nbrMatches, String shelterExpected) {
		assertEquals(shelterExpected, shelterFetched.getAddress());
	}
}
