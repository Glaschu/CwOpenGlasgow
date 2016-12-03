package com.Jamesglasgow.Cw.OpenGlasgow;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.Jamesglasgow.Cw.Async.CarParkAsync;

import com.Jamesglasgow.Cw.models.CarParkRSSitem;


import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

/**
 *
 */
public class ParkingActivity extends BaseActivity {
	LinkedList<CarParkRSSitem> alist = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Adding our layout to parent class frame layout.
		 */
		getLayoutInflater().inflate(R.layout.item_3_layout, frameLayout);
		
		/**
		 * Setting title and itemChecked  
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);


		CarParkRSSitem RoadLog= new CarParkRSSitem();
		String RSSParkURL = "https://api.open.glasgow.gov.uk/traffic/carparks";
		CarParkAsync rssCarparkAsyncParse = new CarParkAsync(this,RSSParkURL);

		try {
			alist = rssCarparkAsyncParse.execute("").get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		//RssRoadListView = (ListView) findViewById(R.id.Roadworklist);
		//RssRoadListView.setAdapter(new RoadListAdapter(this, alist));
	}
}
