package us.wmwm.foursquarelists;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
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
		consumer = new DefaultOAuthConsumer(CLIENT_ID, CLIENT_SECRET);
	}
	
	public boolean isLoggedIn(Context ctx) {
		SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(ctx);
		return p.getString("userID", null)!=null;		
	}
	
}
