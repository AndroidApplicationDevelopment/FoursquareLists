package us.wmwm.foursquarelists.services;

import us.wmwm.foursquarelists.DBHelper;
import us.wmwm.foursquarelists.Venue;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class VenueDAO {

	DBHelper helper;
	
	private static VenueDAO instance;
	
	public static VenueDAO getInstance() {
		return instance;
	}

	public VenueDAO(DBHelper db) {
		helper = db;
		instance = this;
	}
	
	public void save(Venue venue) {
		ContentValues cv = new ContentValues();
		cv.put("name", venue.getName());
		cv.put("id", venue.getId());
		helper.getWritableDatabase().insertWithOnConflict("venue", null, cv,SQLiteDatabase.CONFLICT_REPLACE);
	}
	
	public Cursor getVenues() {
		return helper.getReadableDatabase().rawQuery("select id as _id, name from venue order by name",null);
	}
	
}
