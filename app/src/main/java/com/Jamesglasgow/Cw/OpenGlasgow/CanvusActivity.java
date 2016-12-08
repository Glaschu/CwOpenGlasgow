package com.Jamesglasgow.Cw.OpenGlasgow;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by jamesglasgow on 05/12/2016.
 */

public class CanvusActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * we will use layout inflater to add view in FrameLayout of our base activity layout
         * /
         * Adding our layout to parent class frame layout.
         */
        getLayoutInflater().inflate(R.layout.graph_layout, frameLayout);

        /**
         * Setting title and itemChecked
         */
        //setContentView(R.layout.); // app main UI screen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //Get the budle of the information we sent trough to the app
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Integer Place = extras.getInt("Section");
        String RiseString = extras.getString("Rise");
        String SetString = extras.getString("Set");
        setContentView(new CanvusView(this,Place,RiseString,SetString));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}