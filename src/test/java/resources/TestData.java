package resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	public HashMap<String,Object> add_place_json(String name, String website) throws FileNotFoundException, IOException
	{
		HashMap<String,Object> map = new HashMap<>();
		Read_Data_From_Excel r= new Read_Data_From_Excel();
		ArrayList<String> a=r.getdata("Addlocation","Sheet1");
		map.put("accuracy", a.get(1));
		map.put("name",name);
	    map.put("phone_number", a.get(3));
		map.put("address",a.get(4));
		map.put("website", a.get(5));
		map.put("language", a.get(6));
		String[] typesValue = new String[] { a.get(7), a.get(8) };
		map.put("types", typesValue);
		
		HashMap<String,Object> loc = new HashMap<>();
		loc.put("lat", a.get(9));
		loc.put("lng", a.get(10));
		
		map.put("location", loc);
		return map;
	}

}
