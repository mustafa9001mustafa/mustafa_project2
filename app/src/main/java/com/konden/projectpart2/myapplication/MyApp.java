package com.konden.projectpart2.myapplication;

import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

import com.konden.projectpart2.R;

public class MyApp extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    protected void startMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.m_main);
        mediaPlayer.start();
    }

    protected void stopMediaPlayer() {
        mediaPlayer.stop();
    }

}
