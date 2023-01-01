package com.konden.projectpart2.jopservies;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.konden.projectpart2.sound.Sound;

public class ServiceSoundOnApp extends Service {
    Sound sound = new Sound();

    public ServiceSoundOnApp() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sound.Sound_crate(getApplicationContext());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        sound.sound_start();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sound.sound_stop();

    }
}