import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Sec5_19_1payload;
import files.Sec5_19_ResuableMethods;

public class Sec05_21_IntegrateMultipleAPIs {

	public static void main(String[] args) {
				
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = 
		given().log().all().
		queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Sec5_19_1payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)")
		.extract().body().asString();
		
		System.out.println(response);
		
		//For parsing json using JsonPath class
		JsonPath js=new JsonPath(response);
		String placeID = js.getString("place_id");
		
		System.out.println(placeID);
		
		
		//Use Put Address API to update the address
				String newAddress = "Summer Walk, Africa";
				
				given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body("{\r\n" + 
						"\"place_id\":\""+placeID+"\",\r\n" + 
						"\"address\":\""+newAddress+"\",\r\n" + 
						"\"key\":\"qaclick123\"\r\n" + 
						"}").
				when().put("maps/api/place/update/json")
				.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
				
		// Use Get Place API to verify address in response
		// Since there is no body , no need to pass header in given() method 
				String getPlaceResponse =
				given().log().all().queryParam("place_id", placeID).queryParam("key", "qaclick123")
				.when().get("/maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200)
				.extract().body().asString();
				
				JsonPath js1 = Sec5_19_ResuableMethods.rawToJson(getPlaceResponse);
				String actualAddress = js1.getString("address");
				System.out.println(actualAddress);
				
				Assert.assertEquals(newAddress, actualAddress);	
	}

}
