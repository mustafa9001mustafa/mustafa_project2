package com.konden.projectpart2.sound;

import android.content.Context;
import android.media.MediaPlayer;

import com.konden.projectpart2.R;
import com.konden.projectpart2.appcontroller.AppControllers;

public class Sound {
    MediaPlayer mediaPlayer;

    public void Sound_crate(Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.m_main);
        mediaPlayer.setLooping(true);

    }

    public void sound_start() {
        mediaPlayer.start();
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


    public void sound_stop() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }


    public void volume(float v) {
        mediaPlayer.setVolume(v,v);
    }
}
