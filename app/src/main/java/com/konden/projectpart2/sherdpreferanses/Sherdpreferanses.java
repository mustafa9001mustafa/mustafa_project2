package com.konden.projectpart2.sherdpreferanses;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatDelegate;

import com.konden.projectpart2.appcontroller.AppControllers;


import java.util.Locale;

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

    public void Night(boolean b) {
        editor = sharedPreferences.edit();
        editor.putBoolean("light", b);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        editor.apply();
    }

    public void Light(boolean b) {
        editor = sharedPreferences.edit();
        editor.putBoolean("night", b);
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

    public void setLocale(Activity activity, String languageCode) {
        editor = sharedPreferences.edit();
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        editor.putString(languageCode,"");
        editor.apply();
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
