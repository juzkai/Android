package com.example.myfirstapp.entity;

/**
 * Created by JUZENGKAI on 2018/9/6.
 */

public class Item {
    private int index;
    private String itemName;

    public Item( int index, String itemName) {
        this.index = index;
        this.itemName = itemName;
    }
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
