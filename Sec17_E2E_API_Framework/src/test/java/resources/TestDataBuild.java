package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.S2_AddPlace_Body;
import POJO.S3_Location;
import POJO.S4_DeletePlace_Body;

public class TestDataBuild {

	public S2_AddPlace_Body AddPlace_Payload(String name, String language, String address) {

		// Setting values of all the variables using set method for the body of json
		S2_AddPlace_Body p = new S2_AddPlace_Body();
		p.setAccuracy(100);
		p.setAddress(address);
		p.setLanguage(language);
		p.setLocation(null);
		p.setName(name);
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

		return p;
	}
	
	
	public String DeletePlace_Payload(String place_id) {

		return "{\r\n" + "    \"place_id\":\"" + place_id + "\"\r\n" + "}";

	}

}
