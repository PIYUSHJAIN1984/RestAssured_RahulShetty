import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.Sec5_19_1payload;

public class Sec05_20_ParseJsonResponseBody {

	public static void main(String[] args) {
				
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		//Remove log.all from then() method and assign to a string variable and print out that string variable
		
		String response = 
		given().log().all().
		queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Sec5_19_1payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)")
		.extract().body().asString();
		
		System.out.println(response);
		
		// For parsing json using JsonPath class 
		JsonPath js=new JsonPath(response);
		String placeID = js.getString("place_id");
		
		System.out.println(placeID);
		
	}

}
