package us.wmwm.foursquarelists;

import org.json.JSONObject;

public class Venue {

	String id;
	
	String name;
	
	public Venue(JSONObject obj) {
		id = obj.optString("id");
		JSONObject vn = obj.optJSONObject("venue");
		name = vn.optString("name");
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
