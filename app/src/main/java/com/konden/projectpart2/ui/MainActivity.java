package com.konden.projectpart2.ui;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.ActivityMainBinding;
import com.konden.projectpart2.fragments.fragment_setting.DialogFragmentProfile;
import com.konden.projectpart2.interfases.settings.CallFragment;

import com.konden.projectpart2.jopservies.ServiceSoundOnApp;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;
import com.konden.projectpart2.sound.Sound;

public class MainActivity extends AppCompatActivity implements CallFragment {
    private ActivityMainBinding binding;
    private DialogFragmentProfile back;
    private final Sound sound = new Sound();
    AnimationAll all = new AnimationAll();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onStart() {
        super.onStart();
        ALL_METHOD();
    }

    private void ALL_METHOD() {
        START_BUTTON();
        SETTING_BUTTON();
        EXIT_BUTTON();
        ANIMATIONS();
        F_BUTTON();

    }




    private void F_BUTTON() {
        binding.start.setButtonColor(getColor(R.color.green));
        binding.setting.setButtonColor(getColor(R.color.blue));
        binding.exit.setButtonColor(getColor(R.color.red));

    }

    private void START_BUTTON() {
        binding.start.setOnClickListener(view -> {

            startActivity(new Intent(MainActivity.this, StartPlaying.class));
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            if (Sherdpreferanses.getInstance().GetSoundOther() == true)
                sound.S3();

        });
    }

    private void SETTING_BUTTON() {
        binding.setting.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SettingsApp.class));
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            if (Sherdpreferanses.getInstance().GetSoundOther() == true)
                sound.S3();

        });
    }

    private void EXIT_BUTTON() {
        binding.exit.setOnClickListener(view -> {
            ONS_HOW_DIALOG();
            if (Sherdpreferanses.getInstance().GetSoundOther() == true)
                sound.S3();
        });
    }

    private void ANIMATIONS() {

        all = new AnimationAll();
        binding.setting.setAnimation(all.a1_FromTheTop(MainActivity.this));
        binding.start.setAnimation(all.a1_FromTheTop(MainActivity.this));
        binding.exit.setAnimation(all.a1_FromTheTop(MainActivity.this));
        binding.logoMain.setAnimation(all.a9_Small_to_big(MainActivity.this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void ONS_HOW_DIALOG() {
        back = DialogFragmentProfile.newInstance(getString(R.string.youknow), getString(R.string.Exit), getString(R.string.close));
        back.show(getSupportFragmentManager(), "you");

    }

    @Override
    public void onBackPressed() {
        ONS_HOW_DIALOG();
    }

    @Override
    public void call(boolean x) {
        Intent intent = new Intent(getApplicationContext(), ServiceSoundOnApp.class);
        if (x == true) {
            stopService(intent);
            finish();
        } else if (x == false) {
            back.dismiss();
        }
    }

    @Override
    public void callLanguage(boolean b) {
    }

    @Override
    public void callLanguageClose(boolean b) {
    }
}