package com.Jamesglasgow.Cw.DataBaseManagers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

/**
 * Created by jamesglasgow on 29/11/2016.
 */

public class WeatherDBMan extends SQLiteOpenHelper {

    private static final int DB_VER = 1;
    private static final String DB_PATH = "/data/data/com.Jamesglasgow.Cw.OpenGlasgow/databases/";
    private static final String DB_NAME = "Weather.db";
    private static final String TBL_WEATHERDATA = "WeatherData";

    public static final String COL_INDEX = "Index";
    public static final String COL_ICONNUM = "IconNum";
    public static final String COL_ICONIMG = "IconImg";
    public static final String COL_ICONDES = "IconDes";

    private final Context appContext;

    public WeatherDBMan(Context context, String name,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.appContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_WEATHERDATA_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TBL_WEATHERDATA + "("+COL_INDEX+ "INTEGER PRIMARY KEY,"
                + COL_ICONNUM + " TEXT," + COL_ICONIMG
                + " TEXT," + COL_ICONDES + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_WEATHERDATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
        {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_WEATHERDATA);
            onCreate(sqLiteDatabase);
        }
    }
    // ================================================================================
    // Creates a empty database on the system and rewrites it with your own database.
    // ================================================================================
    public void dbCreate() throws IOException {

        boolean dbExist = dbCheck();

        if(!dbExist){
            //By calling this method an empty database will be created into the default system path
            //of your application so we can overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDBFromAssets();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }
    // ============================================================================================
    // Check if the database already exist to avoid re-copying the file each time you open the application.
    // @return true if it exists, false if it doesn't
    // ============================================================================================
    private boolean dbCheck(){

        //SQLiteDatabase db = null;

        File dbFile = appContext.getDatabasePath(DB_NAME);
        return dbFile.exists();
    }
    // ============================================================================================
    // Copies your database from your local assets-folder to the just created empty database in the
    // system folder, from where it can be accessed and handled.
    // This is done by transfering bytestream.
    // ============================================================================================
    private void copyDBFromAssets() throws IOException{

        InputStream dbInput = null;
        OutputStream dbOutput = null;
        String dbFileName = DB_PATH + DB_NAME;

        try {

            dbInput = appContext.getAssets().open(DB_NAME);
            dbOutput = new FileOutputStream(dbFileName);
            //transfer bytes from the dbInput to the dbOutput
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0) {
                dbOutput.write(buffer, 0, length);
            }

            //Close the streams
            dbOutput.flush();
            dbOutput.close();
            dbInput.close();

        } catch (IOException e)
        {
            throw new Error("Problems copying DB!");
        }
    }
    public WeatherDBManInfo FindWeatherIcon(String IconCode) {

        String query = "Select * FROM " + TBL_WEATHERDATA + " WHERE " + COL_ICONNUM + " =  \"" + IconCode + "\"";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        WeatherDBManInfo WeatherInfo = new WeatherDBManInfo();
        Log.e("weatherDB"," "+cursor.moveToFirst());
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            WeatherInfo.setWeatherID(cursor.getString(1));

            WeatherInfo.setWeatherImg(cursor.getString(2));
            WeatherInfo.setWeatherDes(cursor.getString(3));

            cursor.close();
        } else {
            WeatherInfo = null;
        }
        db.close();
        return WeatherInfo;
    }
}
