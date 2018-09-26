package com.apps.wikex.smallbusinessapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class AppData {
    // Property's  names
    public static final String CARDNAME = "cardname";
    public static final String CARDNUMBER = "cardnumber";
    public static final String SERVICE = "service";
    public static final String CARDCOUNT = "cardcount";

    private static AppData instance = null;
    private ArrayList<Cards> list;
    public int cardCount;

    public static AppData getInstance(){
        if (instance == null){
            instance = new AppData();
        }
        return instance;
    }

    private AppData(){
        list = new ArrayList<>();
    }


    public ArrayList<Cards> getList(){
        return list;
    }

    public static int ramdonNumber(){
        Random rand = new Random();
        return rand.nextInt(1000000);
    }


}


class Cards {
    String nameHolder;
    int number;

    public Cards(String nameHolder, int number) {
        this.nameHolder = nameHolder;
        this.number = number;
    }

    public String getNameHolder() {
        return nameHolder;
    }

    public void setNameHolder(String nameHolder) {
        this.nameHolder = nameHolder;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}