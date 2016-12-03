package com.Jamesglasgow.Cw.OpenGlasgow;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.Jamesglasgow.Cw.Async.NewsAsync;
import com.Jamesglasgow.Cw.Async.WeatherAsync;

import com.Jamesglasgow.Cw.DataBaseManagers.WeatherDBMan;
import com.Jamesglasgow.Cw.DataBaseManagers.WeatherDBManInfo;
import com.Jamesglasgow.Cw.adapters.NewsListAdapter;
import com.Jamesglasgow.Cw.models.NewsRSSitem;
import com.Jamesglasgow.Cw.models.WeatherRSSitem;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

/**
 * Created By james Glasgow
 */
public class NewsActivity extends BaseActivity {
	LinkedList<NewsRSSitem> alist = null;
	private ListView RssNewsListView;
    WeatherDBManInfo WeatherInfo;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        WeatherInfo = new WeatherDBManInfo();

		
		/**
		 * we will use layout inflater to add view in FrameLayout of our base activity layout
		 * /
		 * Adding our layout to parent class frame layout.
		 */
		getLayoutInflater().inflate(R.layout.item_1_layout, frameLayout);
		
		/**
		 * Setting title and itemChecked  
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);

		// Get weather from RSS Feed
		WeatherRSSitem WeatherLog = new WeatherRSSitem();
		String RSSFeedURL = "http://api.openweathermap.org/data/2.5" +
				"/weather?q=glasgow&appid=778031367c64481debd8925b2238952b&mode=xml";


		//
		NewsRSSitem NewsLog= new NewsRSSitem();
		String RSSNewsURL = "http://feeds.skynews.com/feeds/rss/uk.xml";
		WeatherAsync rssAsyncParse = new WeatherAsync(this,RSSFeedURL);
		NewsAsync rssNewsAsyncParse = new NewsAsync(this,RSSNewsURL);
		try {
			WeatherLog = rssAsyncParse.execute("").get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		try {
			alist = rssNewsAsyncParse.execute("").get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		WeatherDBMan WeatherDB = new WeatherDBMan(this,"Weather.db",null,1);
        try {
            WeatherDB.dbCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("WeatherDb ",""+ WeatherLog.getIconId());
       // WeatherDB.findStarSign("Aries");
        WeatherInfo = WeatherDB.FindWeatherIcon(WeatherLog.getIconId());
        if(WeatherInfo==null){
            Log.e("WeatherDb ","Failled to load");
        }
        Log.e("WeatherDb "," "+ WeatherInfo.getWeatherDes());

		RssNewsListView = (ListView) findViewById(R.id.Newslist);
		RssNewsListView.setAdapter(new NewsListAdapter(this, alist));


	}


}
