package com.konden.projectpart2.sound;

import android.media.MediaPlayer;

import com.konden.projectpart2.R;
import com.konden.projectpart2.appcontroller.AppControllers;

public class Sound {
    MediaPlayer mediaPlayer;

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
}
