package com.example.dean.blackbeltpatterns.belt;

import java.util.ArrayList;

/**
 * Created by Dean on 23/12/2017.
 */

public class BeltDegree implements IBelt{

    String mTitle;
    ArrayList<BeltItem> mItems = new ArrayList<>();

    public BeltDegree(String title, ArrayList<BeltItem> items) {
        this.mTitle = title;
        this.mItems = items;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
//
    public ArrayList<BeltItem> getItems() {
        return mItems;
    }
//
    public void setItems(ArrayList<BeltItem> items) {
        this.mItems = items;
    }
}
