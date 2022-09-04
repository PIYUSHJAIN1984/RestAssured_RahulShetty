import files.Sec5_19_1payload;
import io.restassured.path.json.JsonPath;

public class Sec06_26_27_28_complexJsonParse {

	public static void main(String[] args) {
		JsonPath js = new JsonPath(Sec5_19_1payload.coursePrice());

		// Print no of courses returned by API
		// courses is an array because of [] bracket
		int count = js.getInt("courses.size()");
		System.out.println("Count of courses: " + count);

		// Print purchase amount
		int purchaseAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println("purchaseAmt: " + purchaseAmt);

		// Print title of first course
		String firstCourseTitle = js.getString("courses[0].title");
		System.out.println("firstCourseTitle: " + firstCourseTitle);

		// Print all course title and their respective prices
		System.out.println("");
		for (int i = 0; i < count; i++) {
			String title = js.get("courses[" + i + "].title");
			int price = js.get("courses[" + i + "].price");
			System.out.print(i + 1 + " title is: " + title + " " + " and price is: " + price);
			System.out.print("\n");

			// To print directly without using any variable need to conver using toString
			// because sysout always accept String as argument
			// System.out.println(js.get("courses[" + i + "].title").toString());
		}

		// Print no of copies sold by RPA Course
		System.out.println("");
		for (int i = 0; i < count; i++) {
			String title = js.get("courses[" + i + "].title");
			if (title.equalsIgnoreCase("RPA")) {
				int Copies = js.getInt("courses[" + i + "].copies ");
				System.out.println("RPA Copies : " + Copies);
				break;
			}
		}
	}
}