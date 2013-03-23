package us.wmwm.foursquarelists.services;

import java.lang.ref.WeakReference;

import android.app.Service;
import android.os.Binder;

/**
 * Utility {@link Binder} implementation that takes any arbitrary concrete {@link Service}.
 * Binders should never be anonymous, local classes as shown in Android framework samples,
 * so we wrap a {@link WeakReference} around the given concrete service in order to prevent
 * service leaks.
 * @param <T> Type of {@link Service} this binder represents.
 */
public class LocalBinder<T extends Service> extends Binder {

	private WeakReference<T> _service;
	
	public LocalBinder(T service) {
		this.setService(service);
	}
	
	public T getService() {
		return this._service.get();
	}
	
	private void setService(T service) {
		this._service = new WeakReference<T>(service);
	}
	
}