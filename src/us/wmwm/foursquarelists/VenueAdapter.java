package us.wmwm.foursquarelists;

import java.util.HashMap;
import java.util.Map;

import us.wmwm.foursquarelists.services.VenueDAO;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class VenueAdapter extends CursorAdapter {

	Map<String,Integer> fields = new HashMap<String,Integer>();
	
	VenueDAO dao;
	
	public VenueAdapter(Context context, VenueDAO dao) {
		super(context, null, false);
		this.dao = dao;
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

	public void append(FoursquareList list2) {
		swapCursor(dao.getVenues());
		notifyDataSetChanged();
	}
	
	@Override
	public Cursor swapCursor(Cursor newCursor) {
		Cursor c = super.swapCursor(newCursor);
		fields.clear();
		for(int i = 0; i < newCursor.getColumnCount(); i++) {
			String col = newCursor.getColumnName(i);
			fields.put(col, i);
		}
		return newCursor;
		
	}

	@Override
	public void bindView(View v, Context ctx, Cursor cursor) {
		// TODO Auto-generated method stub
		TextView txt = (TextView) v.findViewById(android.R.id.text1);
		txt.setText(cursor.getString(fields.get("name")));
	}

	@Override
	public View newView(Context ctx, Cursor cursor, ViewGroup arg2) {
		return LayoutInflater.from(arg2.getContext()).inflate(android.R.layout.simple_list_item_1, null);		
	}

}
