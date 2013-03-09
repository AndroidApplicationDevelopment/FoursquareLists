package us.wmwm.foursquarelists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class VenueAdapter extends BaseAdapter {

	FoursquareList list;
	
	public VenueAdapter(FoursquareList list) {
		this.list = list;
	}
	
	@Override
	public int getCount() {
		if(list!=null) {
			return list.venues.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(convertView==null) {
			convertView = LayoutInflater.from(arg2.getContext()).inflate(android.R.layout.simple_list_item_1, null);
		} 
		TextView txt = (TextView) convertView.findViewById(android.R.id.text1);
		Venue v = list.venues.get(pos);
		txt.setText(v.getName());
		return convertView;
	}

}
