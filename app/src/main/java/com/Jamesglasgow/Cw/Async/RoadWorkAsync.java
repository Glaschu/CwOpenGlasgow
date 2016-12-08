package com.Jamesglasgow.Cw.Async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.Jamesglasgow.Cw.Parsers.RoadWorkRSSParser;
import com.Jamesglasgow.Cw.models.RoadWorkRSSitem;

import java.util.LinkedList;

/**
 * Created by jamesglasgow on 22/11/2016.
 */

public class RoadWorkAsync extends AsyncTask<String, Integer, LinkedList<RoadWorkRSSitem>> {
    private Context appContext;
    private String urlRSSToParse;
    private ListView RssNewsListView;

    public LinkedList<RoadWorkRSSitem> alist = null;
    public RoadWorkAsync(Context currentAppContext, String urlRSS)
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

    /**
     * The method executed on diffrent thread to the ui,invoked after onPreExecute and
     * will call onPostExecute after it finished and return a Linkedlist
     */
    @Override
    protected LinkedList<RoadWorkRSSitem> doInBackground(String... params)
    {


        RoadWorkRSSParser rssParser = new RoadWorkRSSParser();
        alist=rssParser.ParseStart(urlRSSToParse);

        return alist;
    }

    // A callback method executed on UI thread, invoked after the completion of the task
    // When doInbackground has completed, the return value from that method is passed into this event
    // handler.
    @Override
    protected void onPostExecute(LinkedList<RoadWorkRSSitem> result) {
        // Message to indicate end of parsing

        //RssNewsListView.setAdapter(new RssAdapter(TrafficListingTestProject.this, alist));
        Toast.makeText(appContext,"Parsing finished!", Toast.LENGTH_SHORT).show();
    }
}
