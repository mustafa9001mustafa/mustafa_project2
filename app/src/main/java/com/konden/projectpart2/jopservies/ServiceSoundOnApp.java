//package com.konden.projectpart2.jopservies;
//
//import android.app.Service;
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.os.IBinder;
//
//import com.konden.projectpart2.R;
//import com.konden.projectpart2.appcontroller.AppControllers;
//import com.konden.projectpart2.sound.Sound;
//
//public class ServiceSoundOnApp extends Service {
//    MediaPlayer mediaPlayer;
//
//    public ServiceSoundOnApp() {
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mediaPlayer = MediaPlayer.create(AppControllers.getInstance(), R.raw.m_main);
//        mediaPlayer.setLooping(true);
//
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        super.onStartCommand(intent, flags, startId);
//        mediaPlayer.start();
//        return START_NOT_STICKY;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mediaPlayer.stop();
//        mediaPlayer.release();
//    }
//}