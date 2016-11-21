package com.Jamesglasgow.Cw.models;

import java.io.Serializable;

/**
 * Created by jamesglasgow on 07/11/2016.
 */

public class WeatherRSSitem implements Serializable {
    private String itemPlace;
    private String itemHumidity;
    private String itemTemp;
    private String iconId;
    private String itemSunRise;
    private String itemSunSet;
    public WeatherRSSitem () {
        this.itemPlace = "";
        this.itemTemp = "";
        this.iconId = "";
        this.itemHumidity="";
        this.itemSunRise="";
        this.itemSunSet="";
    }

    public String getItemName() {
        return itemPlace;
    }

    public void setItemName(String itemPlace) {
        this.itemPlace = itemPlace;
    }

    public String getItemDesc() {
        return itemTemp;
    }

    public void setItemDesc(String itemDesc) {
        this.itemTemp = itemDesc;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }

    public String getitemHumidity() {
        return itemHumidity;
    }

    public void setitemHumidity(String itemHumidity) {
        this.itemHumidity = itemHumidity;
    }

    public String getitemSunRise() {
        return itemSunRise;
    }

    public void setitemSunRise(String itemSunRise) {
        this.itemSunRise = itemSunRise;
    }

    public String getitemSunSet() {
        return itemSunSet;
    }

    public void setitemSunSet(String itemSunSet) {
        this.itemSunSet = itemSunSet;
    }
}
