package testone;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import javafx.application.Application;

public class HTTPSRequest {

	int id;

	@Test(priority = 1)
	void getUser() {

		given()

				.when().get("https://reqres.in/api/users?page=2")
				.then().statusCode(200).body("page", equalTo(2)).log().all();

	}

	@Test(priority = 2)
	void createUser() {

		HashMap data = new HashMap();
		data.put("name", "paven");
		data.put("job", "trainer");

		given().contentType("application/json").body(data)
				.when().post("https://reqres.in/api/users")
				.then().statusCode(201).log().all();

	}

	@Test(priority = 3, dependsOnMethods = { "createUser" })
	void putUser() {

		HashMap data = new HashMap();
		data.put("name", "paven");
		data.put("job", "sa");

		given().contentType("application/json").body(data)
				.when().put("https://reqres.in/api/users/" + id)
				.then().statusCode(200).log().all();
	}

	
	@Test(priority = 4, dependsOnMethods = { "createUser" })
	void deletUser()
	{
		given().contentType("application/json")
		.when()
		.delete("https://reqres.in/api/users/" + id)
		.then().statusCode(204).log().all();
	}
}
