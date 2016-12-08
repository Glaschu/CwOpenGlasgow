package com.Jamesglasgow.Cw.Async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.Jamesglasgow.Cw.OpenGlasgow.NewsActivity;
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
    ProgressDialog pdh;
    public LinkedList<NewsRSSitem> alist = null;
    int progress_status;
    private Activity activity;
    public NewsAsync(Activity currentAppContext, String urlRSS)
    {
        appContext = currentAppContext;
        urlRSSToParse = urlRSS;


        pdh= new ProgressDialog(currentAppContext);

    }

    // A callback method executed on UI thread on starting the task
    @Override
    protected void onPreExecute() {
        // Message to indicate start of parsing
        //Toast.makeText(appContext,"Parsing started!", Toast.LENGTH_SHORT).show();
        progress_status = 0;
    }
    /**
     * The method executed on diffrent thread to the ui,invoked after onPreExecute and
     * will call onPostExecute after it finished and return a Linkedlist
     */
    @Override
    protected LinkedList<NewsRSSitem> doInBackground(String... params)
    {

        while(progress_status<100) {

            progress_status += 100;
            NewsRSSParser rssParser = new NewsRSSParser();
            alist = rssParser.ParseStart(urlRSSToParse);
        }


       return alist;
    }

@Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);




        pdh.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdh.setMessage("Loading Feed Please Wait");
        pdh.setIndeterminate(true);
        pdh.setCancelable(false);
        pdh.show();
        //txt_percentage.setText("Downloading " +values[0] + "%");

    }
    // A callback method executed on UI thread, invoked after the completion of the task
    // When doInbackground has completed, the return value from that method is passed into this event
    // handler.
    @Override
    protected void onPostExecute(LinkedList<NewsRSSitem> result) {
        // Message to indicate end of parsing
        pdh.hide();
        //RssNewsListView.setAdapter(new RssAdapter(TrafficListingTestProject.this, alist));
        Toast.makeText(appContext,"Parsing finished!", Toast.LENGTH_SHORT).show();
    }
}
