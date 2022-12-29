package com.konden.projectpart2.sherdpreferanses;

import android.content.Context;
import android.content.SharedPreferences;
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


    public boolean SetTheme(boolean b) {
        editor = sharedPreferences.edit();
        editor.putBoolean("theme", b);
        editor.apply();
        return b;
    }


    public boolean GetTheme() {
        editor = sharedPreferences.edit();
        boolean SwitchCompat_on_off = sharedPreferences.getBoolean("theme", false);
        editor.apply();
        return SwitchCompat_on_off;
    }


    public boolean SetNotify(boolean b) {
        editor = sharedPreferences.edit();
        editor.putBoolean("Notify", b);
        editor.apply();
        return b;
    }


    public boolean GetNotify() {
        editor = sharedPreferences.edit();
        boolean SwitchCompat_on_off = sharedPreferences.getBoolean("Notify", false);
        editor.apply();
        return SwitchCompat_on_off;
    }


    public boolean SetSound(boolean b) {
        editor = sharedPreferences.edit();
        editor.putBoolean("Sound", b);
        editor.apply();
        return b;
    }

    public boolean GetSound() {
        editor = sharedPreferences.edit();
        boolean SwitchCompat_on_off = sharedPreferences.getBoolean("Sound", false);
        editor.apply();
        return SwitchCompat_on_off;
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

//    public void SetLevel(int b, int number) {
//        editor = sharedPreferences.edit();
//        editor.putInt("level_" + b, number);
//        editor.apply();
//    }
//
//    public void GetLevel() {
//        editor = sharedPreferences.edit();
//        boolean SwitchCompat_on_off = sharedPreferences.getBoolean("level_", false);
//        editor.apply();
//    }

    public int SetScore(int x) {
        editor = sharedPreferences.edit();
        editor.putInt("Score", x);
        editor.apply();
        return x;
    }

    public int getScore() {
        editor = sharedPreferences.edit();
        int getScore = sharedPreferences.getInt("Score", 0);
        editor.apply();
        return getScore;
    }

        public boolean isFirstScoreGame() {
        boolean ranBeforeGame = sharedPreferences.getBoolean("RanBeforeScore", false);
        if (!ranBeforeGame) {
            editor = sharedPreferences.edit();
            editor.putBoolean("RanBeforeScore", true);
            editor.apply();
        }
        return !ranBeforeGame;
    }

}
