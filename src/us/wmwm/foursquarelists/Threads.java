package us.wmwm.foursquarelists;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Threads {

	private static ScheduledExecutorService executor;
	
	public static ScheduledExecutorService getExecutor() {
		if(executor==null) {
			executor = Executors.newScheduledThreadPool(4);
		}
		return executor;
	}
	
}
