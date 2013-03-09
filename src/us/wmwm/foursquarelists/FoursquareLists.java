package us.wmwm.foursquarelists;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class FoursquareLists {

	public FoursquareLists(JSONObject obj) {
		JSONObject resp = obj.optJSONObject("response");
		JSONObject lists = resp.optJSONObject("lists");
		JSONArray groups = lists.optJSONArray("groups");
		ids = new ArrayList<String>();
		if(groups==null) {
			return;
		}
		for(int i = 0; i < groups.length(); i++) {
			JSONObject group = groups.optJSONObject(i);
			JSONArray items = group.optJSONArray("items");
			for(int j = 0; j < items.length(); j++) {
				JSONObject item = items.optJSONObject(j);
				ids.add(item.optString("id"));
			}
		}
		
	}
	
	List<String> ids;
	
	public List<String> getIds() {
		return ids;
	}
	
}
