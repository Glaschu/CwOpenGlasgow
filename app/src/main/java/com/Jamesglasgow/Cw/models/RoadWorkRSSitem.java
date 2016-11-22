package com.Jamesglasgow.Cw.models;

import java.io.Serializable;

/**
 * Created by jamesglasgow on 22/11/2016.
 */

public class RoadWorkRSSitem implements Serializable {
    private String itemTitle;
    private String itemDis;
    private String itemGPS;
    private String itemWeb;

    public RoadWorkRSSitem () {
        this.itemTitle = "";
        this.itemDis = "";
        this.itemGPS = "";
        this.itemWeb="";

    }

    public String getItemName() {
        return itemTitle;
    }

    public void setItemName(String itemPlace) {
        this.itemTitle = itemPlace;
    }

    public String getItemDesc() {
        return itemDis;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDis = itemDesc;
    }

    public String getitemWeb() {
        return itemWeb;
    }

    public void setitemWeb(String itemWeb) {
        this.itemWeb = itemWeb;
    }

    public String getitemGPS() {
        return itemGPS;
    }

    public void setitemGPS(String itemGPS) {
        this.itemGPS = itemGPS;
    }
}
