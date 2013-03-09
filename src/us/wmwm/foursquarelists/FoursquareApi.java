package us.wmwm.foursquarelists;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class FoursquareApi {

	private static final String CLIENT_ID="MUSHT2OIZ1RD5Y2EECLK3PSY4Z3L3VEJGJFN2KBN5FTX1XFG";
	
	OAuthConsumer consumer;
	
	OAuthProvider provider;
	
	SharedPreferences prefs;
	
	public FoursquareApi(Context ctx) {
		prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
	}
	
	public void save(String token) {
		prefs.edit().putString("token", token).commit();
	}
	
	public boolean isLoggedIn(Context ctx) {
		SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(ctx);
		return p.getString("token", null)!=null;		
	}
	
	public FoursquareList getLists() throws Exception {
		URL u = new URL("https://api.foursquare.com/v2/users/self/lists");
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setRequestProperty("Authorization", newValue);
	}
	
	public String getRequestUrl() {
		try {
			return String.format("https://foursquare.com/oauth2/authenticate?client_id=%s&response_type=token&redirect_uri=%s",CLIENT_ID,URLEncoder.encode("http://wmwm.us/lists/callback", "utf-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
