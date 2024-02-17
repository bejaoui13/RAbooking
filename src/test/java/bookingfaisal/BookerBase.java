package bookingfaisal;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BookerBase {
	
    protected RequestSpecification reqSpecs;

    @BeforeMethod
	public void beforeTest()
	{
		reqSpecs = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com/").build();

	}
	
	public Response createNewBooking(JSONObject inputUser)
	{
		Response response = RestAssured.given()
				.spec(reqSpecs)
				.contentType(ContentType.JSON)
				.body(inputUser.toString())
				.post("/booking");
	        return response;
	}

}
