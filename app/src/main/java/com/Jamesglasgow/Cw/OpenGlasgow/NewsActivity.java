package com.Jamesglasgow.Cw.OpenGlasgow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Jamesglasgow.Cw.Async.NewsAsync;
import com.Jamesglasgow.Cw.Async.WeatherAsync;

import com.Jamesglasgow.Cw.DataBaseManagers.WeatherDBMan;
import com.Jamesglasgow.Cw.DataBaseManagers.WeatherDBManInfo;
import com.Jamesglasgow.Cw.adapters.NewsListAdapter;
import com.Jamesglasgow.Cw.models.NewsRSSitem;
import com.Jamesglasgow.Cw.models.WeatherRSSitem;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

/**
 * Created By james Glasgow
 */

public class NewsActivity extends BaseActivity {
    SharedPreferences SharedPrefs;

	LinkedList<NewsRSSitem> alist = null;
	private ListView RssNewsListView;
    WeatherDBManInfo WeatherInfo;
    WeatherRSSitem WeatherLog;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout mDrawerLayout;
    int diff;
    TextView Place,Temp,Type;
    ImageView WeatherIcon;
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

        SharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);

		// Get weather from RSS Feed
         WeatherLog = new WeatherRSSitem();
		String RSSFeedURL ="http://api.openweathermap.org/data/2.5/weather?zip="+SharedPrefs.getString("mc_POST","g429xd")
                +",uk&appid=778031367c64481debd8925b2238952b&mode=xml";

		//create ite to store news list
		NewsRSSitem NewsLog= new NewsRSSitem();
		String RSSNewsURL = "https://stv.tv/news/scotland/?format=rss";

		WeatherAsync rssAsyncParse = new WeatherAsync(this,RSSFeedURL);
		NewsAsync rssNewsAsyncParse = new NewsAsync(this,RSSNewsURL);
        //start parse of both async
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
        //Check the data
		WeatherDBMan WeatherDB = new WeatherDBMan(this,"Weather.db",null,1);
        try {
            WeatherDB.dbCreate();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //get data from iconcode
        WeatherInfo = WeatherDB.FindWeatherIcon(WeatherLog.getIconId());

        if(WeatherInfo==null){
            Log.e("WeatherDb ","Failled to load");
        }
        //dispay icon form the data base information
        WeatherIcon=(ImageView)findViewById(R.id.item_icon_imgview);
        String mDrawableName = WeatherInfo.getWeatherImg();
        Resources res = getResources();
        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        WeatherIcon.setImageResource(resID);

        Place=(TextView)findViewById(R.id.Placetxt);
        Temp=(TextView)findViewById(R.id.TempTV);
        Type=(TextView)findViewById(R.id.WeatherTV);
        //The rss freed returns the temp in kelvin
        double tempdec= Double.parseDouble(WeatherLog.getItemDesc());
        tempdec=tempdec - 273.15;
        String tempstring=Double.toString(tempdec);
        //display information
        Place.setText(WeatherLog.getItemName());
        Temp.setText(tempstring);
        Type.setText(WeatherInfo.getWeatherDes());

        //caldiffrence of times for sunrise and set
        diff=CalTimeDiff(WeatherLog.getitemSunRise(),WeatherLog.getitemSunSet());

		RssNewsListView = (ListView) findViewById(R.id.Newslist);
		RssNewsListView.setAdapter(new NewsListAdapter(this, alist));


	}
    //to cal the time diffrence between the sun rise and set to now when high noon is
    public int CalTimeDiff(String rise,String set){
        int finalAnswer;

        String riseNew=rise.replace("T", " ");

        String setNew=set.replace("T", " ");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date RiseDate = new Date();
        try {
            RiseDate = dateFormat.parse(riseNew);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        Date SetDate = new Date();
        try {
            SetDate = dateFormat.parse(setNew);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        Calendar calendar3 = Calendar.getInstance();
        calendar1.setTime(RiseDate);
        calendar2.setTime(SetDate);

        long milsecs1= calendar1.getTimeInMillis();
        long milsecs2 = calendar2.getTimeInMillis();
        long milsecs3 = calendar3.getTimeInMillis();

        long diff2 = milsecs3 - milsecs1;
        long diff3 = milsecs2-milsecs3;

        long dminutes2 = diff2 / (60 * 1000);
        long dminutes3 = diff3 / (60 * 1000);

        if(dminutes2<dminutes3){
            long answer= dminutes2/20;
             finalAnswer=Math.round(answer);
        }else{
            long answer= dminutes3/20;
             finalAnswer=Math.round(answer);
        }
        if(finalAnswer<0){
            finalAnswer=0;
        }
        Log.e("Time",""+finalAnswer);

        return  finalAnswer;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_news, menu);
        return super.onCreateOptionsMenu(menu);

    }
    //add a button to action bar to activite draw
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle

		switch (item.getItemId()) {

            case R.id.Graph:

                Intent intent = new Intent(this, CanvusActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("Section",diff);
                extras.putString("Rise",WeatherLog.getitemSunRise());
                extras.putString("Set",WeatherLog.getitemSunSet());
                Log.e("Helloworld",WeatherLog.getitemSunSet());
                intent.putExtras(extras);
                startActivity(intent);
                return true;
		default:
			return super.onOptionsItemSelected(item);
		}
    }
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view

        menu.findItem(R.id.Graph).setVisible(true);

        return super.onPrepareOptionsMenu(menu);
    }


}
