package com.konden.projectpart2.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.konden.projectpart2.R;
import com.konden.projectpart2.appcontroller.AppControllers;

public class Sound {
    public static Sound instance;
    MediaPlayer mediaPlayer;


    private Sound() {
    }

    public static Sound getInstance() {
        if (instance == null)
            instance = new Sound();
        return instance;
    }

    public void S1() {
        mediaPlayer = MediaPlayer.create(AppControllers.getInstance(), R.raw.sound1);
        mediaPlayer.start();
    }

    public void S3() {
        mediaPlayer = MediaPlayer.create(AppControllers.getInstance(), R.raw.sound3);
        mediaPlayer.start();
    }

    public void S4() {
        mediaPlayer = MediaPlayer.create(AppControllers.getInstance(), R.raw.sound4);
        mediaPlayer.start();
    }

    public void S5() {
        mediaPlayer = MediaPlayer.create(AppControllers.getInstance(), R.raw.sound5);
        mediaPlayer.start();
    }

    public void Win_end() {
        mediaPlayer = MediaPlayer.create(AppControllers.getInstance(), R.raw.winer_end);
        mediaPlayer.start();
    }


    public void Final_game_Timer() {
        mediaPlayer = MediaPlayer.create(AppControllers.getInstance(), R.raw.final_count_down_timer);
        mediaPlayer.start();
    }

    public void sound_stop() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
