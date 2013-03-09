package us.wmwm.foursquarelists;

import us.wmwm.foursquarelists.fragments.LoginFragment;
import us.wmwm.foursquarelists.fragments.LoginFragment.OnUserLoginListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

	FoursquareApi api = new FoursquareApi();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(!api.isLoggedIn(this)) {
			showLogin();
		}
	}

	private void showLogin() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		LoginFragment lf = new LoginFragment();
		lf.setFoursquareApi(api);
		lf.setOnUserLogin(onUserLoginListener);
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
			finish();
		}
	};

}
