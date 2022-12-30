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


    public boolean isFirstTime() {
        boolean ranBefore = sharedPreferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            editor = sharedPreferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.apply();
        }
        return !ranBefore;
    }

    public boolean isFirstTimeOther() {
        boolean ranBefore = sharedPreferences.getBoolean("RanBeforeOther", false);
        if (!ranBefore) {
            editor = sharedPreferences.edit();
            editor.putBoolean("RanBeforeOther", true);
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

    public boolean isNotFirstMainGame() {
        boolean ranBeforeGame = sharedPreferences.getBoolean("isFirstMainGame", false);
        if (!ranBeforeGame) {
            editor = sharedPreferences.edit();
            editor.putBoolean("isFirstMainGame", true);
            editor.apply();
        }

        return ranBeforeGame;
    }

    public boolean isNotFirstMainGame2() {
        boolean ranBeforeGame = sharedPreferences.getBoolean("isFirstMainGame2", false);
        if (!ranBeforeGame) {
            editor = sharedPreferences.edit();
            editor.putBoolean("isFirstMainGame2", true);
            editor.apply();
        }

        return ranBeforeGame;
    }

    public void SetWin(int x) {
        editor = sharedPreferences.edit();
        editor.putInt("win", x);
        editor.apply();
    }

    public int getWin() {
        editor = sharedPreferences.edit();
        int getWin = sharedPreferences.getInt("win", 0);
        editor.apply();
        return getWin;
    }


    public void SetLoser(int x) {
        editor = sharedPreferences.edit();
        editor.putInt("loser", x);
        editor.apply();
    }

    public int getLoser() {
        editor = sharedPreferences.edit();
        int getLoser = sharedPreferences.getInt("loser", 0);
        editor.apply();
        return getLoser;
    }


    public void SetFinished(int x) {
        editor = sharedPreferences.edit();
        editor.putInt("finished", x);
        editor.apply();
    }

    public int getFinished() {
        editor = sharedPreferences.edit();
        int getFinished = sharedPreferences.getInt("finished", 0);
        editor.apply();
        return getFinished;
    }

    public void Set_Id_Questions(int x, int i) {
        editor = sharedPreferences.edit();
        editor.putInt("id_questions", x);
        editor.putInt("id_level", i);
        editor.apply();
    }

    public int get_Id_Questions() {
        editor = sharedPreferences.edit();
        int getFinished = sharedPreferences.getInt("id_questions", 0);
        editor.apply();
        return getFinished;
    }

    public int get_Id_Level() {
        editor = sharedPreferences.edit();
        int getFinished = sharedPreferences.getInt("id_level", 0);
        editor.apply();
        return getFinished;
    }

    public void SetSoundBackGrand(boolean b) {
        editor = sharedPreferences.edit();
        editor.putBoolean("SoundBack", b);
        editor.apply();
    }

    public boolean GetSoundBackGrand() {
        editor = sharedPreferences.edit();
        boolean SwitchCompat_on_off_s = sharedPreferences.getBoolean("SoundBack", false);
        editor.apply();
        return SwitchCompat_on_off_s;
    }

    public void SetSoundOther(boolean b) {
        editor = sharedPreferences.edit();
        editor.putBoolean("SoundOther", b);
        editor.apply();
    }

    public boolean GetSoundOther() {
        editor = sharedPreferences.edit();
        boolean SwitchCompat_on_off_b = sharedPreferences.getBoolean("SoundOther", false);
        editor.apply();
        return SwitchCompat_on_off_b;
    }

    public float SetKD(float x, float i) {
        float s = x / i;
        editor = sharedPreferences.edit();
        editor.putFloat("KD", s);
        editor.apply();
        return s;
    }

    public float GetKD() {
        editor = sharedPreferences.edit();
        float getWin = sharedPreferences.getFloat("KD", 2.1f);
        editor.apply();
        return getWin;
    }

    public void clear() {
        editor = sharedPreferences.edit();
        editor.remove("KD");
        editor.remove("Notify");
        editor.remove("Score");
        editor.remove("win");
        editor.remove("SoundBack");
        editor.remove("SoundOther");
        editor.remove("id_level");
        editor.remove("finished");
        editor.remove("RanBeforeOther");
        editor.apply();
    }
}
