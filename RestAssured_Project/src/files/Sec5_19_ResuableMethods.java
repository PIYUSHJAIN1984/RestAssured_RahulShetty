package files;

import io.restassured.path.json.JsonPath;

public class Sec5_19_ResuableMethods {

	public static JsonPath rawToJson(String response) {

		// String converted to Json using JsonPath
		JsonPath js1 = new JsonPath(response);
		return js1;
	}

}
