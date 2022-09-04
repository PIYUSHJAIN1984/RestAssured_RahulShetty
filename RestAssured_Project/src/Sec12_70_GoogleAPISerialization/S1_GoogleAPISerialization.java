package Sec12_70_GoogleAPISerialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class S1_GoogleAPISerialization {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		//Setting values of all the variables using set method for the body of json
		S2_AddPlace_Body p = new S2_AddPlace_Body();
		p.setAccuracy(100);
		p.setAddress("351 NS Road");
		p.setLanguage("English");
		p.setLocation(null);
		p.setName("Amit");
		p.setPhone_number("09889999");
		p.setTypes(null);
		p.setWebsite("www.google.com");

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("park");
		p.setTypes(myList);

		S3_Location s = new S3_Location();
		s.setLat(-38.3);
		s.setLng(-45.3);
		p.setLocation(s);

		//Pass the object p created in above steps in the body
		Response res = given().queryParam("key", "=qaclick123").when().log().all().body(p).post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response();

		String responseStr = res.asString();
		System.out.println(responseStr);

	}

}
