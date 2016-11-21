package com.Jamesglasgow.Cw.models;

import java.io.Serializable;

/**
 * Created by jamesglasgow on 14/11/2016.
 */

public class NewsRSSitem implements Serializable {
    private String itemTitle;
    private String itemDis;
    private String itemImage;
    private String itemWeb;

    public NewsRSSitem () {
        this.itemTitle = "";
        this.itemDis = "";
        this.itemImage = "";
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

    public String getIconId() {
        return itemImage;
    }

    public void setIconId(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getitemWeb() {
        return itemWeb;
    }

    public void setitemWeb(String itemHumidity) {
        this.itemWeb = itemWeb;
    }


}
