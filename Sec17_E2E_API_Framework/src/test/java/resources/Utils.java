package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification reqSpec;
	ResponseSpecification resSpec;

	public RequestSpecification requestSpecification() throws IOException {

		if (reqSpec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseURL"))
					.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
					.setRelaxedHTTPSValidation()
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return reqSpec;
		}
		return reqSpec;
	}

	public ResponseSpecification responseSpecification() {
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return resSpec;
	}

	public String getGlobalProperties(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\PJain15\\RestAssured_RahulShetty\\Sec17_E2E_API_Framework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		return js.get(key).toString();
	}
}
