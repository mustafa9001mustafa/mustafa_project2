package com.konden.projectpart2.sherdpreferanses;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

import com.konden.projectpart2.appcontroller.AppControllers;

public class Sherdpreferanses {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    private static Sherdpreferanses instance;

    public static Sherdpreferanses getInstance() {
        if (instance == null) {
            instance = new Sherdpreferanses();
        }
        return instance;
    }

    private Sherdpreferanses() {
        sharedPreferences = AppControllers.getInstance().getSharedPreferences("mode", Context.MODE_PRIVATE);
    }

    public void Night() {
        boolean b1 = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        editor = sharedPreferences.edit();
        editor.putBoolean("light", b1);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        editor.apply();
    }

    public void Light() {
        boolean x =  AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO;
        editor = sharedPreferences.edit();
        editor.putBoolean("night", x);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        editor.apply();
    }

    public boolean isFirstTime() {
        boolean ranBefore = sharedPreferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            editor = sharedPreferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.apply();
        }
        return !ranBefore;
    }

    public boolean isFirstTimeGame() {
        boolean ranBeforeGame = sharedPreferences.getBoolean("RanBeforeGame", false);
        if (!ranBeforeGame) {
            editor = sharedPreferences.edit();
            editor.putBoolean("RanBeforeGame", true);
            editor.apply();
        }
        return !ranBeforeGame;
    }
}
