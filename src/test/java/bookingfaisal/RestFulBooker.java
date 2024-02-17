package bookingfaisal;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class RestFulBooker {
	
	  @Test
	    public void healthCheckTest()
	    {
	        //BDD style test
	        given().when().get("https://restful-booker.herokuapp.com/ping").
	                    then().
	                    assertThat().statusCode(201);
	    }

}
