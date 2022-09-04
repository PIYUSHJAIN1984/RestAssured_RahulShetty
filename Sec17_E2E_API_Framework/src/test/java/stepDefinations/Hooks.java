package stepDefinations;

import java.io.IOException;
import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {

		// write a code that will give you place ID
		// execute this code only when placeID is null

		stepDefinations SD = new stepDefinations();

		// Since place ID is static so java tells to call object using Class name
		// instead of object name
		if (stepDefinations.place_id == null) {
			SD.add_place_api_payload_with("ABCD", "FRENCG", "34 ssd 232 pune");
			SD.user_calls_using_http_request("AddPlaceAPI", "POST");
			SD.verify_place_id_created_maps_to_using("ABCD", "getPlaceAPI");
		}
	}
}