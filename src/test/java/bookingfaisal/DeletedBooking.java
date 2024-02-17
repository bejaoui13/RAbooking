package bookingfaisal;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeletedBooking extends BookerBase {

	@Test
	public void deleteBook() {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("firstname", "Naeem");
		jsonObject.put("lastname", "Mal");
		jsonObject.put("totalprice", 111);
		jsonObject.put("depositpaid", true);
		jsonObject.put("additionalneeds", "Baby Chair");

		JSONObject jsondates = new JSONObject();
		jsondates.put("checkin", "2022-02-01");
		jsondates.put("checkout", "2022-02-03");
		jsonObject.put("bookingdates", jsondates);

		Response responsebook = createNewBooking(jsonObject);
		responsebook.prettyPrint();

		int id = responsebook.jsonPath().getInt("bookingid");
		System.out.println(id);

		Response responseDelet = RestAssured.given().auth().preemptive().basic("admin", "password123")
				.delete(String.format("https://restful-booker.herokuapp.com/booking/%s", id));

		Assert.assertEquals(responseDelet.getStatusCode(), 201, "Response is not 200.");
		Response responseAfterdelete = RestAssured
				.get(String.format("https://restful-booker.herokuapp.com/booking/%s", id));

		Assert.assertEquals(responseAfterdelete.body().asString(), "Not Found", "Object not deleted.");

	}

}
