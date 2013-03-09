package us.wmwm.foursquarelists.fragments;

import us.wmwm.foursquarelists.FoursquareApi;
import us.wmwm.foursquarelists.R;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginFragment extends Fragment {

	WebView webView;
		
	String accessToken;
	
	FoursquareApi foursquareApi;
	
	public static interface OnUserLoginListener {
		void onLogin(String token);
	}
	
	OnUserLoginListener onUserLogin;
	
	public void setOnUserLogin(OnUserLoginListener onUserLogin) {
		this.onUserLogin = onUserLogin;
	}
	
	public void setFoursquareApi(FoursquareApi foursquareApi) {
		this.foursquareApi = foursquareApi;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		View root = inflater.inflate(R.layout.fragment_login, container,false);
		webView = (WebView) root.findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new WebChromeClient() {
			
		});
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				System.out.println(url);
				Uri uri = Uri.parse(url);
				String fragment = uri.getFragment();
				if(fragment!=null && fragment.contains("access_token=")) {
					String token = fragment.split("=")[1];
					accessToken = token;
					if(onUserLogin!=null) {
						onUserLogin.onLogin(token);
					}
					return true;
				}
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		return root;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		String url = foursquareApi.getRequestUrl();
		System.out.println(url);
		webView.loadUrl(url);
	}

}
