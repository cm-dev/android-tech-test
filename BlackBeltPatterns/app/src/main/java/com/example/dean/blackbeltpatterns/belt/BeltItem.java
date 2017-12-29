package com.example.dean.blackbeltpatterns.belt;

/**
 * Created by Dean on 23/12/2017.
 */

public class BeltItem implements IBelt{

    private String mName;

    public BeltItem(String name) {
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
