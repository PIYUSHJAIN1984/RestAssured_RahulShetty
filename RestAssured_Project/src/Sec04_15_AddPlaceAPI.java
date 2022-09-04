import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class Sec04_15_AddPlaceAPI {

	public static void main(String[] args) {
		// Validate if addPlace API is working as expected
		
		//Given : Add Input Details
		//When: Submit the API : resource and http method type will be entered here
		//Then: Validate the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		given().log().all().
		queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house1\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3927\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}")
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
	}

}
