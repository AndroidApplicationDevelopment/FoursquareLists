package us.wmwm.foursquarelists;

import java.net.URLEncoder;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class FoursquareApi {

	private static final String CLIENT_ID="MUSHT2OIZ1RD5Y2EECLK3PSY4Z3L3VEJGJFN2KBN5FTX1XFG";
	
	private static final String CLIENT_SECRET="YFA4VVV4A05POOIVOXNFR5TCNN2ONQ5YJKCBBUAXCEIWEA4M";
	
	OAuthConsumer consumer;
	
	OAuthProvider provider;
	
	public FoursquareApi() {
		// TODO Auto-generated constructor stub
		//consumer = new DefaultOAuthConsumer(CLIENT_ID, CLIENT_SECRET);
		//provider = new DefaultOAuthProvider(null, accessTokenEndpointUrl, authorizationWebsiteUrl)
	}
	
	public boolean isLoggedIn(Context ctx) {
		SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(ctx);
		return p.getString("userID", null)!=null;		
	}
	
	public String getRequestUrl() {
		try {
			return String.format("https://foursquare.com/oauth2/authenticate?client_id=%s&response_type=token&redirect_uri=%s",CLIENT_ID,URLEncoder.encode("http://wmwm.us/lists/callback", "utf-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
