package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestData {

	public AddPlace add_place(String name, String website)
	{
		AddPlace A= new AddPlace();
		A.setAccuracy(50);
		A.setAddress("18 ShreinTHJI");
		A.setLanguage("gUJ");
		A.setPhone_number("(+91) 983 893 3937");
		A.setWebsite(website);
		A.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		A.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		A.setLocation(l);
		return A;
		
	}
	
	public Object delete_place(String placeid)
	{
		return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}";

	}

}
