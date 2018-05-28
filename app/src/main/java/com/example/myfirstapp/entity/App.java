package com.example.myfirstapp.entity;

public class App {
    private int aIcon;
    private String aName;
    private String subName;

    public App() {}

    public App(int aIcon, String aName, String subName) {
        this.aIcon = aIcon;
        this.aName = aName;
        this.subName = subName;
    }

    public int getaIcon() {
        return aIcon;
    }

    public void setaIcon(int aIcon) {
        this.aIcon = aIcon;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
