package com.example.dogsappv2.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

/**
 * This class helps us to save and retrieve data from sharedPreferences
 */
public class SharedpreferencesHelper {
    // Create some variable for singleton
    private static final String PREF_TIME = "pref time";
    private static SharedpreferencesHelper instance;
    private SharedPreferences prefs;

    // Constructor
    private SharedpreferencesHelper(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    // instance method
    public static SharedpreferencesHelper getInstance(Context context){
        if (instance == null){
            instance = new SharedpreferencesHelper(context);
        }
        return instance;
    }

    /**
     * Create method to store data in SharedPreferences
     */
    public void saveUpdateTime(long time){
        prefs.edit().putLong(PREF_TIME, time).apply();
    }
    /**
     * Create method to retrieve data from SharedPreferences
     */
    public long getUpdateTime(){
       return prefs.getLong(PREF_TIME,0);
    }
}
