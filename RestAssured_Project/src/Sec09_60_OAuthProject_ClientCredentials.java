import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;

public class Sec09_60_OAuthProject_ClientCredentials {

	public static void main(String[] args) throws InterruptedException {
		
		//Step2 : write code for getting accessToken
		//Rest assured encode special chars to numbers. To stop it use urlEncodingEnabled(false)
		String accessTokenResponse = given().urlEncodingEnabled(false)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("grant_type", "client_credentials")
		.queryParam("scope", "https://www.googleapis.com/auth/userinfo.email")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js=new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		
		
		//Step1 : Code written for getCourse API 
		String response = given().log().all().queryParam("access_token", accessToken)
		.when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
	}

}
