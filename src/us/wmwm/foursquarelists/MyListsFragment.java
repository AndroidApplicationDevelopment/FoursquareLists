package us.wmwm.foursquarelists;

import java.util.concurrent.Future;

import us.wmwm.foursquarelists.services.FoursquareService;
import us.wmwm.foursquarelists.services.FoursquareService.ListListener;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MyListsFragment extends Fragment {

	ListView list;
	
	Future<?> loadListsFuture;
	
	FoursquareApi foursquareApi;
	
	FoursquareService service;
	
	VenueAdapter adapter;
	
	public void setService(FoursquareService service) {
		this.service = service;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		service.updateLists(listListener);
	}
	
	ListListener listListener = new ListListener() {
		
		@Override
		public void onFoursquareLists(FoursquareLists list) {
			//
			System.out.println("onFoursquareLists");
		}
		
		@Override
		public void onFoursquareList(final FoursquareList list) {
			System.out.println("onFoursquareList");
			Activity activity = getActivity();
			if(activity==null) {
				return;
			}
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					adapter.append(list);
				}
			});
			
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my_lists,container,false);
		list = (ListView) view.findViewById(R.id.list);
		return view;
	}
	
}
