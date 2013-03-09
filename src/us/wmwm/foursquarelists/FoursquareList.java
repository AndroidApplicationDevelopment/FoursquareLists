package us.wmwm.foursquarelists;

import java.util.List;

import org.json.JSONObject;

public class FoursquareList {

	public FoursquareList(JSONObject obj) {
		
	}
	
	String type;
	String name;
	int count;

	String id;
	
	List<Venue> venues;
	
}
