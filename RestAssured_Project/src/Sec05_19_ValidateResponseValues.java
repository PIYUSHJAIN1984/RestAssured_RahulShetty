import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.Sec5_19_1payload;

public class Sec05_19_ValidateResponseValues {

	public static void main(String[] args) {
		// Validate if addPlace API is working as expected
		
		//Given : Add Input Details
		//When: Submit the API : resource and http method type will be entered here
		//Then: Validate the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		given().log().all().
		queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Sec5_19_1payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)");
	}

}
