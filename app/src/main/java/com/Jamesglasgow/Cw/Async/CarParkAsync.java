package com.Jamesglasgow.Cw.Async;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.Jamesglasgow.Cw.Parsers.CarParkParser;
import com.Jamesglasgow.Cw.models.CarParkRSSitem;


import java.util.LinkedList;

/**
 * Created by jamesglasgow on 23/11/2016.
 */

public class CarParkAsync extends AsyncTask<String, Integer, LinkedList<CarParkRSSitem>> {
    private Context appContext;
    private String urlRSSToParse;
    private ListView RssParkListView;

    public LinkedList<CarParkRSSitem> alist = null;
    public CarParkAsync(Context currentAppContext, String urlRSS)
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
    protected LinkedList<CarParkRSSitem> doInBackground(String... params)
    {


        CarParkParser rssParser = new CarParkParser();
        alist=rssParser.ParseStart(urlRSSToParse);

        return alist;
    }

    // A callback method executed on UI thread, invoked after the completion of the task
    // When doInbackground has completed, the return value from that method is passed into this event
    // handler.
    @Override
    protected void onPostExecute(LinkedList<CarParkRSSitem> result) {
        // Message to indicate end of parsing

        Toast.makeText(appContext,"Parsing finished!", Toast.LENGTH_SHORT).show();
    }
}
