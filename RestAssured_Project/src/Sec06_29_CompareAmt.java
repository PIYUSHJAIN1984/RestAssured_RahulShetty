import org.testng.Assert;
import org.testng.annotations.Test;

import files.Sec5_19_1payload;
import io.restassured.path.json.JsonPath;

//Verify if Sum of all Course prices matches with Purchase Amount
public class Sec06_29_CompareAmt {

	@Test
	public void sumOfCourses() {
		JsonPath js = new JsonPath(Sec5_19_1payload.coursePrice());

		// Print no of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println("Count of courses: " + count);

		// Print purchase amount
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println("purchaseAmt: " + purchaseAmt);

		// Verify if all course prices matches with Purchase Amount
		System.out.println("");

		int expectedAmt = 0;
		for (int i = 0; i < count; i++) {
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies ");
			expectedAmt = expectedAmt + (price * copies);
		}
		System.out.println("Expected amount: " + expectedAmt);
		Assert.assertEquals(expectedAmt, purchaseAmt);
	}
}