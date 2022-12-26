package com.konden.projectpart2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.CompoundButton;

import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.R;
import com.konden.projectpart2.appcontroller.AppControllers;
import com.konden.projectpart2.databinding.ActivitySettingsAppBinding;
import com.konden.projectpart2.fragments.fragment_setting.DialogFragmentBack;
import com.konden.projectpart2.fragments.fragment_setting.DialogFragmentLanguage;
import com.konden.projectpart2.fragments.fragment_setting.DialogFragmentTheme;
import com.konden.projectpart2.interfases.CallBoolTheme;
import com.konden.projectpart2.interfases.CallFragment;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;
import com.yariksoffice.lingver.Lingver;

import java.util.Locale;

public class SettingsApp extends AppCompatActivity implements CallFragment, CallBoolTheme {
    private ActivitySettingsAppBinding binding;
    private DialogFragmentBack back;
    private DialogFragmentLanguage language;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySettingsAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        CheckMode();
        if (Locale.getDefault().getLanguage().equals("en"))
            binding.backIconS.setRotation(90);

    }

    private void CheckMode() {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            binding.switch3.setChecked(true);
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            binding.switch3.setChecked(false);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ALL_METHOD();

    }

    private void ALL_METHOD() {
        PROFILE_BUTTON();
        ANIMATIONS();
        ON_BACK_CLICK();
        F_BUTTON();
        BUTTON_RESET();
        SWITCH3();
        LANGUAGES();
    }


    private void BUTTON_RESET() {
        binding.reset.setOnClickListener(view -> {
            back = DialogFragmentBack.newInstance(getString(R.string.doyoudelete), getString(R.string.reset), getString(R.string.close));
            back.show(getSupportFragmentManager(), "reset");
        });
    }

    private void F_BUTTON() {
        binding.reset.setButtonColor(getColor(R.color.red));
        binding.profile.setButtonColor(getColor(R.color.green));

    }

    private void LANGUAGES() {

        binding.card4.setOnClickListener(view -> {
            language = DialogFragmentLanguage.newInstance(getString(R.string.choosethelanguage), getString(R.string.Arabic), getString(R.string.English));
            language.show(getSupportFragmentManager(), "lan");
        });

    }

    private void SWITCH3() {
        binding.switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                DialogFragmentTheme theme = DialogFragmentTheme.newInstance(getString(R.string.textout), getString(R.string.restart), b);
                theme.show(getSupportFragmentManager(), "theme");
            }
        });
    }

    private void ON_BACK_CLICK() {
        binding.backIconS.setOnClickListener(view -> {
            startActivity(new Intent(SettingsApp.this, MainActivity.class));
//            overridePendingTransition(R.anim.fadein, R.anim.shake);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);


        });
    }


    private void ANIMATIONS() {
        AnimationAll all = new AnimationAll();
        binding.card1.setAnimation(all.a4_FromTheBottom(SettingsApp.this));
        binding.card2.setAnimation(all.a4_FromTheBottom(SettingsApp.this));
        binding.card3.setAnimation(all.a4_FromTheBottom(SettingsApp.this));
        binding.card4.setAnimation(all.a4_FromTheBottom(SettingsApp.this));
        binding.card5.setAnimation(all.a4_FromTheBottom(SettingsApp.this));
        binding.view2.setAnimation(all.a2_FromTheRight(SettingsApp.this));
        binding.view1.setAnimation(all.a3_FromTheLeft(SettingsApp.this));
        binding.v2.setAnimation(all.a2_FromTheRight(SettingsApp.this));
        binding.v1.setAnimation(all.a3_FromTheLeft(SettingsApp.this));
        binding.backIconS.setAnimation(all.a2_FromTheRight(SettingsApp.this));
    }

    private void PROFILE_BUTTON() {
        binding.profile.setOnClickListener(view -> {
            startActivity(new Intent(SettingsApp.this, Profile.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        });
    }


    @Override
    public void call(boolean x) {
        if (x == true) {
            // todo
        } else if (x == false) {
            back.dismiss();
        }
    }

    @Override
    public void callLanguage(boolean b) {
        if (b == true) {
            Lingver.getInstance().setLocale(getApplicationContext(), "ar");
        } else {
            Lingver.getInstance().setLocale(getApplicationContext(), "en");
        }
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        language.dismiss();

    }

    @Override
    public void callLanguageClose(boolean b) {
        if (b == true)
            language.dismiss();
    }

    @Override
    public void callboll(boolean b) {
        if (b == true) {
            Sherdpreferanses.getInstance().Night();
        } else {
            Sherdpreferanses.getInstance().Light();
        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SettingsApp.this, MainActivity.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
