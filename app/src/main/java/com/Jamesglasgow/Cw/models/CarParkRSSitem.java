package com.Jamesglasgow.Cw.models;

import java.io.Serializable;

/**
 * Created by jamesglasgow on 23/11/2016.
 */

public class CarParkRSSitem implements Serializable {
    private String itemName;
    private String itemOccupied;
    private String itemCapacity;
    private String itemLong;
    private String itemLat;
    private String itemPercent;

    public CarParkRSSitem () {
        this.itemName = "";
        this.itemOccupied = "";
        this.itemCapacity = "";
        this.itemLong="";
        this.itemLat="";
        this.itemPercent="";


    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemOccupied() {
        return itemOccupied;
    }

    public void setItemOccupied(String itemOccupied) {
        this.itemOccupied = itemOccupied;
    }

    public String getitemCapacity() {
        return itemCapacity;
    }

    public void setitemCapacity(String itemCapacity) {
        this.itemCapacity = itemCapacity;
    }

    public String getitemLong() {
        return itemLong;
    }

    public void setItemLong(String itemLong) {
        this.itemLong = itemLong;
    }

    public String getitemLat() {
        return itemLat;
    }

    public void setItemLat(String itemLat) {
        this.itemLat = itemLat;
    }

    public String getitemPercent() {
        return itemPercent;
    }

    public void setitemPercent(String itemPercent) {
        this.itemPercent = itemPercent;
    }
}