package com.konden.projectpart2.ui;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.appcontroller.AppControllers;
import com.konden.projectpart2.constant.FinalContract;
import com.konden.projectpart2.databinding.ActivitySplachScreenBinding;
import com.konden.projectpart2.room.ViewModelGame;
import com.konden.projectpart2.room.game.level.LevelEntity;
import com.konden.projectpart2.room.game.questios.QuestionsEntity;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class SplashScreenApp extends AppCompatActivity {
    private ActivitySplachScreenBinding binding;
    private int progressStatus = 0, sleep = 16;
//    private ProfileEntity profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fullScreen();

        super.onCreate(savedInstanceState);
        binding = ActivitySplachScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        CheckDataInsert();

    }

    @Override
    protected void onStart() {
        super.onStart();
        ALL_METHOD();
    }

    private void fullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


//    private void NOTIFICATION_SWISHIEST() {
//
//        ComponentName name = new ComponentName(getBaseContext(), MyServices.class);
//        JobInfo info = new JobInfo.Builder(FinalContract.Job_id, name)
//                .setPeriodic((24 * 60 * 60 * 1000),
//                        JobInfo.getMinFlexMillis())
//                .build();
//        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//
//        if (Sherdpreferanses.getInstance().GetNotify() == true) {
//            if (Sherdpreferanses.getInstance().isNotFirstMainGame2()) {
//                scheduler.cancel(FinalContract.Job_id);
//            }
//            scheduler.schedule(info);
//        } else {
//            scheduler.cancel(FinalContract.Job_id);
//
//        }
//
//    }

    private void ALL_METHOD() {
        TIMER_SPLASH();
        ANIMATIONS();

    }


    private void CheckDataInsert() {
//        viewModel = new ViewModelProvider(this).get(ViewModelGame.class);
//        if (Sherdpreferanses.getInstance().isFirstTimeGame()) {
//            profile = new ProfileEntity(getString(R.string.player), getString(R.string.example), getString(R.string.birth), getString(R.string.male), getString(R.string.palestine));
//            viewModel.insertProfile(profile);
//
//        }

        if (Sherdpreferanses.getInstance().isFirstTimeOther()) {
            sleep = 41;
            Sherdpreferanses.getInstance().SetUnlockNow(2);
            Sherdpreferanses.getInstance().SetFinished(0);
            Sherdpreferanses.getInstance().SetWin(0);
            Sherdpreferanses.getInstance().SetLoser(0);
            Sherdpreferanses.getInstance().SetScore(2);
            Sherdpreferanses.getInstance().SetSoundOther(true);
            Sherdpreferanses.getInstance().SetTimerEnd(true);
            Sherdpreferanses.getInstance().SetGameImageNumber(1);
            Sherdpreferanses.getInstance().SetLevelImageNumber(1);


            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                Sherdpreferanses.getInstance().SetTheme(true);
            else
                Sherdpreferanses.getInstance().SetTheme(false);
        }
    }

    private void ANIMATIONS() {
        AnimationAll all = new AnimationAll();
        binding.nameApp.setAnimation(all.a4_FromTheBottom(SplashScreenApp.this));
        binding.progressBar.setAnimation(all.a4_FromTheBottom(SplashScreenApp.this));
    }

//    private void CHECKSERVES() {
//        Intent intent1 = new Intent(getApplicationContext(), ServiceSoundOnApp.class);
//        if (Sherdpreferanses.getInstance().GetTimerEnd() == false)
//            stopService(new Intent(getApplicationContext(), MainActivity.class));
//        else
//            startService(intent1);
//
//        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
//    }

    private void TIMER_SPLASH() {

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += FinalContract.PLUS_PROGRESS;
                    binding.progressBar.setProgress(progressStatus);

                    if (progressStatus == 100) {
                        Intent intent = new Intent(new Intent(getApplicationContext(), MainActivity.class));
                        startActivity(intent);
//                        CHECKSERVES();
                        if (Sherdpreferanses.getInstance().GetTheme())
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        else
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }



    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void recreate() {
        finish();
        startActivity(getIntent());
        finish();
    }
}