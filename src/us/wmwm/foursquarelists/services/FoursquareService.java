package us.wmwm.foursquarelists.services;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import us.wmwm.foursquarelists.FoursquareApi;
import us.wmwm.foursquarelists.FoursquareList;
import us.wmwm.foursquarelists.FoursquareLists;
import us.wmwm.foursquarelists.MyListsFragment;
import us.wmwm.foursquarelists.Threads;
import us.wmwm.foursquarelists.VenueAdapter;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FoursquareService extends Service {

	Map<String, Future<?>> futures = new HashMap<String, Future<?>>();

	FoursquareApi api;

	private static final String UPDATE_LISTS = "update_lists";

	@Override
	public void onCreate() {
		super.onCreate();
		api = new FoursquareApi(this);
	}

	public static interface ListListener {

		void onFoursquareLists(FoursquareLists list);

		void onFoursquareList(FoursquareList list);

	}

	// guava and auto...
	public void updateLists(final ListListener listener) {
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
						listener.onFoursquareList(list);
						// runOnUiThread(new Runnable() {
						// @Override
						// public void run() {
						// if(adapter==null) {
						// MyListsFragment.this.list.setAdapter(adapter = new
						// VenueAdapter(list));
						// } else {
						// adapter.append(list);
						// }
						// }
						// });
					}
				} catch (Exception e) {

				}
			}
		});

		futures.put(UPDATE_LISTS, updateFuture);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new LocalBinder<FoursquareService>(this);
	}

}
