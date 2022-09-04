import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;

public class Sec09_56_OAuthProject_AuthType {

	public static void main(String[] args) throws InterruptedException {
		
		//Bottom up approach 
		//First step write code for getCourse
		//Second step write code for getting accessToken
		//Third step to get authorization code
		
		// Step3 : to get authorization code
		// Google does not allow to use automation for login so we need to manually pass
		// url in code instead of login to url and get the code after signIn
		// "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");

		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AdQt8qiKM6EqV1uBkzZNbZfciRHdYSBwkbcVbIWtAqv511BVDQn1AUaltZnnFUGCTV36EQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
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
		String response = given().log().all().queryParam("access_token", accessToken)
		.when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
	}

}
