package us.wmwm.foursquarelists.fragments;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import us.wmwm.foursquarelists.FoursquareApi;
import us.wmwm.foursquarelists.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class LoginFragment extends Fragment {

	WebView webView;
	
	ScheduledExecutorService executor;
	
	String tokenUrl;
	String tokenSecret;
	String verifier;
	
	FoursquareApi foursquareApi;
	
	public void setFoursquareApi(FoursquareApi foursquareApi) {
		this.foursquareApi = foursquareApi;
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		executor = Executors.newScheduledThreadPool(1);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		View root = inflater.inflate(R.layout.fragment_login, container,false);
		webView = (WebView) root.findViewById(R.id.webview);
		return root;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		webView.loadUrl("http://google.com");
	}
	
}
