package us.wmwm.foursquarelists;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class FoursquareList {

	List<Venue> venues;
	
	public FoursquareList(JSONObject obj) {
		JSONObject resp = obj.optJSONObject("response");
		JSONObject list = resp.optJSONObject("list");
		JSONObject items = list.optJSONObject("listItems");
		venues = new ArrayList<Venue>();
		for(int i = 0; i < items.length(); i++) {			
			JSONArray itm = items.optJSONArray("items");
			for(int j = 0; j < itm.length(); j++) {
				Venue v = new Venue(itm.optJSONObject(j));
				venues.add(v);
			}
		}
	}
	
	public List<Venue> getVenues() {
		return venues;
	}
	
}
