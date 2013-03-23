package us.wmwm.foursquarelists.services;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import us.wmwm.foursquarelists.DBHelper;
import us.wmwm.foursquarelists.FoursquareApi;
import us.wmwm.foursquarelists.FoursquareList;
import us.wmwm.foursquarelists.FoursquareLists;
import us.wmwm.foursquarelists.Threads;
import us.wmwm.foursquarelists.Venue;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FoursquareService extends Service {

	Map<String, Future<?>> futures = new HashMap<String, Future<?>>();

	FoursquareApi api;

	private static final String UPDATE_LISTS = "update_lists";
	
	DBHelper database;
	VenueDAO venues;
	
	@Override
	public void onCreate() {
		super.onCreate();
		api = new FoursquareApi(this);
		database = new DBHelper(this);
		venues = new VenueDAO(database);
	}

	public static interface ListListener {

		void onFoursquareLists(FoursquareLists list);

		void onFoursquareList(FoursquareList list);

	}

	// guava and auto...
	public Future updateLists(final ListListener listener) {
		Future<?> updateFuture = futures.get(UPDATE_LISTS);

		if (updateFuture != null) {
			updateFuture.cancel(true);
		}

		updateFuture = Threads.getExecutor().submit(new Runnable() {
			@Override
			public void run() {
				try {
					FoursquareLists l = api.getLists();
					listener.onFoursquareLists(l);
					for (int i = 0; i < l.getIds().size(); i++) {
						final FoursquareList list = api.getList(l.getIds().get(
								i));
						for(Venue venue : list.getVenues()) {
							venues.save(venue);
						}
						listener.onFoursquareList(list);
					}
				} catch (Exception e) {

				}
			}
		});

		futures.put(UPDATE_LISTS, updateFuture);
		return updateFuture;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new LocalBinder<FoursquareService>(this);
	}

}
