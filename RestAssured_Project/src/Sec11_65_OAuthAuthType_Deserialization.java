import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import Sec11_63_pojo.S1_GetCourse;
import Sec11_63_pojo.S3_WebAutomation;
import Sec11_63_pojo.S4_API;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;


//Copy of Sec9_56_OAuthProject_AuthType.java class 

public class Sec11_65_OAuthAuthType_Deserialization {

	public static void main(String[] args) throws InterruptedException {
		
		//Bottom up approach 
		//First step write code for getCourse
		//Second step write code for getting accessToken
		//Third step to get authorization code
		
		// Step3 : to get authorization code
		// Google does not allow to use automation for login so we need to manually pass
		// url in code instead of login to url and get the code after signIn
		// "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
        //Step 25 has to be changed everytime using PostMan Oauth_RahulSh -> getCode by login to chrome browser get request url 
		
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qhPgXK6qRSe4gskFGqHnsRvzk4Rtrp6ypZKmOMh_niEyuYjrvrt_Y516J4fKGl_WA&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&scope")[0];
		System.out.println(code);
		
		//Step2 : write code for getting accessToken
		//Rest assured encode special chars to numbers. To stop it use urlEncodingEnabled(false)
		String accessTokenResponse = given().urlEncodingEnabled(false)
		.queryParams("code",code)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js=new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		
		
		
		//Step1 : Code written for getCourse API 
		
		//Use POJO class to extract response instead of getting respose in String
		S1_GetCourse response = given().log().all().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php").as(S1_GetCourse.class);
		
		//expect().defaultParser(Parser.JSON) is used to tell response is in JSON/XML format.
		
		
		//To pring values of variable in json
		System.out.println(response.getLinkedIn());
		System.out.println(response.getInstructor());
		
		
		// Problem : Get Price of "courseTitle": "SoapUI Webservices testing",
		List<S4_API> apiCourses = response.getCourses().getAPI();
		int countCourses = apiCourses.size();
		for (int i = 0; i < countCourses; i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice());
				break;
			}
		}
		
		
		// Problem: Print all courseTitle of webAutomation	
		String[] courseTitles = { "Selenium Webdriver Java", "Cypress", "Protractor" };
		ArrayList<String> actualList = new ArrayList<String>();

		List<S3_WebAutomation> webCourses = response.getCourses().getWebAutomation();
		for (int i = 0; i < webCourses.size(); i++) {
			System.out.println(webCourses.get(i).getCourseTitle());
			actualList.add(webCourses.get(i).getCourseTitle());
		}

		List<String> expectedList = Arrays.asList(courseTitles);
		Assert.assertTrue(expectedList.equals(actualList));
	}

}
