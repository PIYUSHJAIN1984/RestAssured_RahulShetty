import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Sec5_19_1payload;
import files.Sec5_19_ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Sec07_35_Parameterization {
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		String response = given().log().all().header("Content-Type", "text/plain")
	    .body(Sec5_19_1payload.addBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js= Sec5_19_ResuableMethods.rawToJson(response);
		String id = js.get("ID");
	    System.out.println(id);
	}
	
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		// multi dimensional array
		return new Object[][] { { "123s", "567" }, { "123t", "568" }, { "123u", "569" } };

	}

}
