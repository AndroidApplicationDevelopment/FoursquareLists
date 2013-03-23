package us.wmwm.foursquarelists;

import us.wmwm.foursquarelists.fragments.LoginFragment;
import us.wmwm.foursquarelists.fragments.LoginFragment.OnUserLoginListener;
import us.wmwm.foursquarelists.services.FoursquareService;
import us.wmwm.foursquarelists.services.LocalBinder;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

	FoursquareApi api;
	
	FoursquareService service;
	
	Long startService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		api = new FoursquareApi(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
		Intent serviceIntent = new Intent(this, FoursquareService.class);
		ServiceConnection conn = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				service = null;
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public void onServiceConnected(ComponentName name, IBinder s) {
				System.out.println("diff " + (System.currentTimeMillis() - startService));
				service = ((LocalBinder<FoursquareService>)s).getService();
				if(!api.isLoggedIn(MainActivity.this)) {
					showLogin();
				} else {
					showLists();
				}
				
			}
		};
		startService = System.currentTimeMillis();
		bindService(serviceIntent, conn, Service.BIND_AUTO_CREATE);
	}

	private void showLogin() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		LoginFragment lf = new LoginFragment();
		lf.setFoursquareApi(api);
		lf.setOnUserLogin(onUserLoginListener);
		ft.replace(R.id.fragment_content, lf,ft.getClass().getName());
		ft.commit();
	}
	
	private void showLists() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		MyListsFragment lf = new MyListsFragment();
		lf.setService(service);
		ft.replace(R.id.fragment_content, lf,ft.getClass().getName());
		ft.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private OnUserLoginListener onUserLoginListener = new OnUserLoginListener() {
		@Override
		public void onLogin(String token) {
			api.save(token);
			showLists();
		}
	};

}
