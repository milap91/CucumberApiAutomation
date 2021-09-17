package stepdefinations;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResouces;

import resources.TestData;
import resources.utils;
public class stepdefination extends utils {
	RequestSpecification reqsp;
	//ResponseSpecification ressp;
	Response response;
	TestData t= new TestData();
	static String place_id;

	

@Given("Payload is present with {string} and {string}")
public void payload_is_present_with_and(String name, String website) throws IOException {
//reqsp=given().spec(reqspecification_subset()).body(t.add_place(name, website));
reqsp=given().spec(reqspecification_subset()).body(t.add_place_json(name, website));
}
    
@When("usercalls {string} with {string} method")
public void usercalls_with_method(String Resource, String method) {
	APIResouces resourceAPI=APIResouces.valueOf(Resource);
	System.out.println(resourceAPI.getResource());
	if(method.equalsIgnoreCase("POST"))
	{
		response= reqsp.when().post(resourceAPI.getResource());
	}
	else if (method.equalsIgnoreCase("GET")) 
	{
		response= reqsp.when().get(resourceAPI.getResource());
		
	}
	else if (method.equalsIgnoreCase("PUT")) 
	{
		response= reqsp.when().put(resourceAPI.getResource());
		
	}
	else if (method.equalsIgnoreCase("DELETE")) 
	{
		response= reqsp.when().delete(resourceAPI.getResource());
		
	}
}
@Then("API call got success with status code {int}")
public void api_call_got_success_with_status_code(Integer int1) {
	assertEquals(response.getStatusCode(),200);
}
@Then("verify {string} in response is {string}")
public void verify_in_response_is(String key, String expectedvalue) {
   assertEquals(getJsonParserKeyValue(response,key),expectedvalue);
    
}

@Then("verify {string} created maps to {string} using {string}")
public void verify_created_maps_to_using(String queryparmeter, String expectedname, String resource) throws IOException {
	place_id=getJsonParserKeyValue(response, queryparmeter);
    reqsp=given().spec(reqspecification_subset()).queryParam("place_id",place_id);
    usercalls_with_method(resource,"GET");
    assertEquals(getJsonParserKeyValue(response,"name"), expectedname);
    
}

@Given("Payload is present for deleteAPI")
public void payload_is_present_for_delete_api() throws IOException {
    reqsp=given().spec(reqspecification_subset()).body(t.delete_place(place_id));
}

}
