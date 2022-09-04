import org.testng.annotations.Test;

import files.Sec5_19_1payload;
import files.Sec5_19_ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sec07_38_StaticJson {
	
	//Json is in external file
	//Convert content of json to String -> for this read content of file and convert to Bytes -> Byte data to String
	
	@Test
	public void addBook() throws IOException {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		given().log().all().
		queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(
				Paths.get("C:\\Users\\PJain15\\RestAssured_RahulShetty\\4_DemoProject\\src\\files\\Sec7_38_AddPlace.txt"))))
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
	}
	}
