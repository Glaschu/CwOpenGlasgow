package com.Jamesglasgow.Cw.SaveFiles;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by jamesglasgow on 07/12/2016.
 */

public class SaveData extends Activity {

    // *********************************************
    // Declare variables etc.
    // *********************************************
    SharedPreferences SharedPrefs;


    private int Maptype;
    private String PostCode;



    // *********************************************
    // Declare getters and setters etc.
    // *********************************************

    private void setMaptype(int Maptype)
    {
        this.Maptype = Maptype;
    }

    public int getMaptype()
    {
        return Maptype;
    }

    private void setPostCode(String PostCode)
    {
        this.PostCode = PostCode;
    }

    public String getPostCode()
    {
        return PostCode;
    }



// **************************************************
// Declare constructor and date manipulation methods.
// **************************************************

    public SaveData(SharedPreferences SDPrefs){
        setMaptype(1);
        setPostCode("G40BA");

        try {
            this.SharedPrefs = SDPrefs;
        }
        catch (Exception e)
        {
            Log.e("n","Pref Manager is NULL" );
        }
        setDefaultPrefs();
    }

    public void savePreferences(String key, boolean value) {
        SharedPreferences.Editor editor = SharedPrefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void savePreferences(String key, String value) {
        SharedPreferences.Editor editor = SharedPrefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void savePreferences(String key, int value) {
        SharedPreferences.Editor editor = SharedPrefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void setDefaultPrefs(){
        savePreferences("mc_TYPE", 1);
        savePreferences("mc_POST", "g428yd");
    }


}