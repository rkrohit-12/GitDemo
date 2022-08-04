package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.restassured.response.Response;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import pojo.AddPlace;
import pojo.Location;
import resources.TestDataBuild;
import resources.utils;

public class stepDefination extends utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	String place_id;

	/*
	 * @Given("^Add Place Payload$") public void add_place_payload() throws
	 * IOException {
	 */

	@Given("Add Place Payload with {String} {String} {String}")
	public void add_place_payload_with_something_something_something(String name, String address, String language)
			throws Throwable {

		/*
		 * resspec = new
		 * ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.
		 * JSON).build();
		 */

		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, address, language));

	}

	@When("^user calls \"([^\"]*)\" with Post http request$")
	public void user_calls_something_with_post_http_request(String strArg1) throws Throwable {

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		response = res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
	}

	@Then("^the API call got success with status code 200$")
	public void the_api_call_got_success_with_status_code_200() throws Throwable {

		assertEquals(response.getStatusCode(), 200);
	}

	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void something_in_response_body_is_something(String keyValue, String Expectedvalue) throws Throwable {

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(), Expectedvalue);

	}

	@Then("^verify palce_Id created maps to {string} using {string}")
	public void verify_palceid_created_maps_to_something_using_something(String string, String string2) {

	}
	
	@Given("^DeletePlace Payload$")
	public void deleteplace_payload() throws IOException {

		given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
}
