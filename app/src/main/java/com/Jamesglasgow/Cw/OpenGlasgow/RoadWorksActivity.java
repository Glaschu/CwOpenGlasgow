package com.Jamesglasgow.Cw.OpenGlasgow;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.Jamesglasgow.Cw.Async.NewsAsync;
import com.Jamesglasgow.Cw.Async.RoadWorkAsync;
import com.Jamesglasgow.Cw.adapters.NewsListAdapter;
import com.Jamesglasgow.Cw.adapters.RoadListAdapter;
import com.Jamesglasgow.Cw.models.RoadWorkRSSitem;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

/**
 *
 */
public class RoadWorksActivity extends BaseActivity {
	LinkedList<RoadWorkRSSitem> alist = null;
	private ListView RssRoadListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Adding our layout to parent class frame layout.
		 */
		getLayoutInflater().inflate(R.layout.item_2_layout, frameLayout);
		
		/**
		 * Setting title and itemChecked  
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
		


		RoadWorkRSSitem RoadLog= new RoadWorkRSSitem();
		String RSSRoadURL = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
		RoadWorkAsync rssRoadAsyncParse = new RoadWorkAsync(this,RSSRoadURL);

		try {
			alist = rssRoadAsyncParse.execute("").get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		RssRoadListView = (ListView) findViewById(R.id.Roadworklist);
		RssRoadListView.setAdapter(new RoadListAdapter(this, alist));
	}
}
