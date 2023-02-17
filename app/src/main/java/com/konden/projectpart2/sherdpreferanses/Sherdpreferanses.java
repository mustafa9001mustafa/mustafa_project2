package com.konden.projectpart2.sherdpreferanses;

import android.content.Context;
import android.content.SharedPreferences;

import com.konden.projectpart2.appcontroller.AppControllers;

public class Sherdpreferanses {
    private static Sherdpreferanses instance;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private Sherdpreferanses() {
        sharedPreferences = AppControllers.getInstance().getSharedPreferences("mode", Context.MODE_PRIVATE);
    }

    public static Sherdpreferanses getInstance() {
        if (instance == null) {
            instance = new Sherdpreferanses();
        }
        return instance;
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
//
//
//    public boolean SetNotify(boolean b) {
//        editor = sharedPreferences.edit();
//        editor.putBoolean("Notify", b);
//        editor.apply();
//        return b;
//    }
//
//
//    public boolean GetNotify() {
//        editor = sharedPreferences.edit();
//        boolean SwitchCompat_on_off = sharedPreferences.getBoolean("Notify", false);
//        editor.apply();
//        return SwitchCompat_on_off;
//    }


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

    public boolean isFirstTimeRGame2() {
        boolean ranBeforeGame = sharedPreferences.getBoolean("RBeforeGame2", false);
        if (!ranBeforeGame) {
            editor = sharedPreferences.edit();
            editor.putBoolean("RBeforeGame2", true);
            editor.apply();
        }
        return !ranBeforeGame;
    }


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

    public boolean isNotFirstGameCheck() {
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

        for (int i = 0; i < x; i++) {
            editor.putInt("finished" + x, x);
        }
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

    public void SetTimerEnd(boolean b) {
        editor = sharedPreferences.edit();
        editor.putBoolean("SoundBack", b);
        editor.apply();
    }

    public boolean GetTimerEnd() {
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

    public int SetUnlockNow(int x) {
        editor = sharedPreferences.edit();
        editor.putInt("LevelNow", x);
        editor.apply();
        return x;
    }

    public int getUnlockNow() {
        editor = sharedPreferences.edit();
        int getScore = sharedPreferences.getInt("LevelNow", 0);
        editor.apply();
        return getScore;
    }

    public int SetCoinShop(int x) {
        editor = sharedPreferences.edit();
        editor.putInt("CoinShop", x);
        editor.apply();
        return x;
    }

    public int GetCoinShop() {
        editor = sharedPreferences.edit();
        int getScore = sharedPreferences.getInt("CoinShop", 0);
        editor.apply();
        return getScore;
    }


    public void SetImageLevel2(boolean b) {
        editor = sharedPreferences.edit();
        editor.putBoolean("SetImageLevel2", b);
        editor.apply();
    }

    public boolean GetImageLevel2() {
        editor = sharedPreferences.edit();
        boolean SwitchCompat_on_off_s = sharedPreferences.getBoolean("SetImageLevel2", false);
        editor.apply();
        return SwitchCompat_on_off_s;
    }


    public void SetImageLevel3(boolean b) {
        editor = sharedPreferences.edit();
        editor.putBoolean("SetImageLevel3", b);
        editor.apply();
    }

    public boolean GetImageLevel3() {
        editor = sharedPreferences.edit();
        boolean SwitchCompat_on_off_s = sharedPreferences.getBoolean("SetImageLevel3", false);
        editor.apply();
        return SwitchCompat_on_off_s;
    }


    public void SetImageGame2(boolean b) {
        editor = sharedPreferences.edit();
        editor.putBoolean("ImageGame2", b);
        editor.apply();
    }

    public boolean GetImageGame2() {
        editor = sharedPreferences.edit();
        boolean SwitchCompat_on_off_s = sharedPreferences.getBoolean("ImageGame2", false);
        editor.apply();
        return SwitchCompat_on_off_s;
    }


    public void SetImageGame3(boolean b) {
        editor = sharedPreferences.edit();
        editor.putBoolean("ImageGame3", b);
        editor.apply();
    }

    public boolean GetImageGame3() {
        editor = sharedPreferences.edit();
        boolean SwitchCompat_on_off_s = sharedPreferences.getBoolean("ImageGame3", false);
        editor.apply();
        return SwitchCompat_on_off_s;
    }



    public int SetLevelImageNumber(int x) {
        editor = sharedPreferences.edit();
        editor.putInt("LevelImageNumber", x);
        editor.apply();
        return x;
    }

    public int GetLevelImageNumber() {
        editor = sharedPreferences.edit();
        int getScore = sharedPreferences.getInt("LevelImageNumber", 0);
        editor.apply();
        return getScore;
    }




    public int SetGameImageNumber(int x) {
        editor = sharedPreferences.edit();
        editor.putInt("GameImageNumber", x);
        editor.apply();
        return x;
    }

    public int GetGameImageNumber() {
        editor = sharedPreferences.edit();
        int getScore = sharedPreferences.getInt("GameImageNumber", 0);
        editor.apply();
        return getScore;
    }



    public void clear() {
        editor = sharedPreferences.edit();
        editor.remove("KD ");
        editor.remove("Notify");
        editor.remove("Score");
        editor.remove("win");
        editor.remove("SoundBack");
        editor.remove("SoundOther");
        editor.remove("id_level");
        editor.remove("finished");
        editor.remove("RanBeforeOther");
        editor.remove("RanBefore");
        editor.remove("CheckBox");
        editor.apply();
    }
}