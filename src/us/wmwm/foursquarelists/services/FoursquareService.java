package us.wmwm.foursquarelists.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FoursquareService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return new LocalBinder<FoursquareService>(this);
	}

}
