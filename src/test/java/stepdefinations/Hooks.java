package stepdefinations;

import java.io.IOException;

import io.cucumber.java.Before;


public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{		//execute this code only when place id is null
		//write a code that will give you place id
		
		stepdefination m =new stepdefination();
		if(stepdefination.place_id==null)
		{
		
		m.payload_is_present_with_and("Shetty", "French");
		m.usercalls_with_method("AddPlaceAPI", "POST");
		m.verify_created_maps_to_using("place_id","Milap_Soni", "GetPlaceAPI");
		}
		
		

	}
}
