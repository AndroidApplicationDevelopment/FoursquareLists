package us.wmwm.foursquarelists;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class FoursquareList {

	List<Venue> venues;
	
	public FoursquareList(JSONObject obj) {
		JSONObject resp = obj.optJSONObject("response");
		JSONArray lists = resp.optJSONArray("lists");
		venues = new ArrayList<Venue>();
		for(int i = 0; i < lists.length(); i++) {
			JSONObject list = lists.optJSONObject(i);
			JSONObject items = list.optJSONObject("listItems");
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
