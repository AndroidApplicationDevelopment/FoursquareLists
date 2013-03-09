package us.wmwm.foursquarelists;

import java.util.concurrent.Future;

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
	
	VenueAdapter adapter;
	
	public void setFoursquareApi(FoursquareApi foursquareApi) {
		this.foursquareApi = foursquareApi;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Threads.getExecutor().submit(loadLists);
	}
	
	Runnable loadLists = new Runnable() {
		public void run() {
			try {
				FoursquareLists l = foursquareApi.getLists();
				for(int i = 0; i < l.getIds().size(); i++) {
					final FoursquareList list = foursquareApi.getList(l.getIds().get(i));
					System.out.println(list);
					getActivity().runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if(adapter==null) {
								MyListsFragment.this.list.setAdapter(adapter = new VenueAdapter(list));
							} else {
								adapter.append(list);
							}
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_my_lists,container,false);
		list = (ListView) view.findViewById(R.id.list);
		return view;
	}
	
}
