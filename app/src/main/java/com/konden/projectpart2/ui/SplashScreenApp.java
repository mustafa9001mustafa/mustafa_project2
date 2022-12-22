package com.konden.projectpart2.ui;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.konden.projectpart2.R;
import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.databinding.ActivitySplachScreenBinding;


public class SplashScreenApp extends AppCompatActivity {
    ActivitySplachScreenBinding binding;
    private int progressStatus = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplachScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onStart() {
        super.onStart();
        ALL_METHOD();
    }

    private void ALL_METHOD() {
        TIMER_SPLASH();
        ANIMATIONS();
    }

    private void ANIMATIONS() {
        // Todo
        AnimationAll all = new AnimationAll();
        binding.sp.setAnimation(all.a9_Small_to_big(SplashScreenApp.this));
        binding.nameApp.setAnimation(all.a4_FromTheBottom(SplashScreenApp.this));
        binding.progressBar.setAnimation(all.a4_FromTheBottom(SplashScreenApp.this));
    }


    private void TIMER_SPLASH() {

        new Thread(new Runnable() {
            public void run() {

                while (progressStatus < 100) {
                    progressStatus += 1;
                    binding.progressBar.setProgress(progressStatus);

                    if (progressStatus == 100)
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);

                    try {
                        Thread.sleep(30);
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
}