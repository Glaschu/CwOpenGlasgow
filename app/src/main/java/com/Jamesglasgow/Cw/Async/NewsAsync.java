package com.Jamesglasgow.Cw.Async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.Jamesglasgow.Cw.Parsers.NewsRSSParser;
import com.Jamesglasgow.Cw.models.NewsRSSitem;

import java.util.LinkedList;

/**
 * Created by jamesglasgow on 22/11/2016.
 */

public class NewsAsync extends AsyncTask<String, Integer, LinkedList<NewsRSSitem>> {
    private Context appContext;
    private String urlRSSToParse;
    private ListView RssNewsListView;

    public LinkedList<NewsRSSitem> alist = null;
    public NewsAsync(Context currentAppContext, String urlRSS)
    {
        appContext = currentAppContext;
        urlRSSToParse = urlRSS;

    }

    // A callback method executed on UI thread on starting the task
    @Override
    protected void onPreExecute() {
        // Message to indicate start of parsing
        Toast.makeText(appContext,"Parsing started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected LinkedList<NewsRSSitem> doInBackground(String... params)
    {

        //Htmlparser info =new Htmlparser();
        //alist=info.ParseStart(IncidentUrl);
        //NewsRSSitem parsedData;
        NewsRSSParser rssParser = new NewsRSSParser();
        alist=rssParser.ParseStart(urlRSSToParse);
        //try {
          //alist=rssParser.parseRSSData(urlRSSToParse);
        //} catch (MalformedURLException e) {
          //  e.printStackTrace();
        //}
        //parsedData = rssParser.getRSSDataItem();

       return alist;
    }

    // A callback method executed on UI thread, invoked after the completion of the task
    // When doInbackground has completed, the return value from that method is passed into this event
    // handler.
    @Override
    protected void onPostExecute(LinkedList<NewsRSSitem> result) {
        // Message to indicate end of parsing

        //RssNewsListView.setAdapter(new RssAdapter(TrafficListingTestProject.this, alist));
        Toast.makeText(appContext,"Parsing finished!", Toast.LENGTH_SHORT).show();
    }
}
