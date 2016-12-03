package com.Jamesglasgow.Cw.DataBaseManagers;

import java.io.Serializable;

/**
 * Created by jamesglasgow on 03/12/2016.
 */
public class WeatherDBManInfo implements Serializable {

// *********************************************
// Declare variables etc.
// *********************************************

    private String WeatherID;
    private String WeatherImg;
    private String WeatherDes;


    private static final long serialVersionUID = 0L;
// *********************************************
// Declare getters and setters etc.
// *********************************************


    public String getWeatherID() {
        return WeatherID;
    }

    public void setWeatherID(String WeatherID) {
        this.WeatherID = WeatherID;
    }

    public String getWeatherImg() {
        return WeatherImg;
    }

    public void setWeatherImg(String WeatherImg) {
        this.WeatherImg = WeatherImg;
    }

    public String getWeatherDes() {
        return WeatherDes;
    }

    public void setWeatherDes(String WeatherDes) {
        this.WeatherDes = WeatherDes;
    }
    /*
    @Override
    public String toString() {
        String WeatherData;
        WeatherData = "mcStarSignsInfo [starSignID=" + starSignID;
        WeatherData = ", starSign=" + starSign;
        WeatherData = ", starSignCharacteristics=" + starSignCharacteristics +"]";
        return WeatherData;
    }
*/
}