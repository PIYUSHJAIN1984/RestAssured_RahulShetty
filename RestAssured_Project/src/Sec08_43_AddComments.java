import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class Sec08_43_AddComments {

	public static void main(String[] args) {
		
		RestAssured.baseURI= "http://localhost:8080";
		SessionFilter session=new SessionFilter();
		
		//Code written for Login API and get session filter
		given().log().all().header("Content-Type","application/json")
		.body("{ \"username\": \"piyushmnit200\", \r\n"
				+ "  \"password\": \"Aabha@351\" \r\n"
				+ "}")
		.filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().extract().response().asString();
		
		//Code written for Add Comment
		String expectedMsg ="Adding comment through JIRA";
		String addCommentResponse = given().log().all().pathParam("key", "10004").header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"body\": \""+expectedMsg+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}")
		.filter(session)
		.when().post("/rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		//Get the id of comment newly added 
		JsonPath js=new JsonPath(addCommentResponse);
		String commentID = js.getString("id");
		
		
		//Code for Add attachment
		given().log().all().pathParam("key", "10004").header("Content-Type","multipart/form-data")
		.header("X-Atlassian-Token","no-check")
		.multiPart("file",new File("C:\\Users\\PJain15\\RestAssured_RahulShetty\\4_DemoProject\\src\\files\\Sec8_46_AttachmentFile.txt"))
		.filter(session)
		.when().post("/rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		
		
		
		
		
			
		}
	}
