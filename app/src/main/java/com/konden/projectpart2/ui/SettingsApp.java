package com.konden.projectpart2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.ActivitySettingsAppBinding;
import com.konden.projectpart2.fragments.dialog_sound.DialogFragmentSound;
import com.konden.projectpart2.fragments.fragment_setting.DialogFragmentProfile;
import com.konden.projectpart2.fragments.fragment_setting.DialogFragmentLanguage;
import com.konden.projectpart2.interfases.settings.CallFragment;
import com.konden.projectpart2.interfases.settings.ListenerCallSounds;
import com.konden.projectpart2.jopservies.ServiceSoundOnApp;
import com.konden.projectpart2.room.ViewModelGame;
import com.konden.projectpart2.room.profile.ProfileEntity;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;
import com.konden.projectpart2.sound.Sound;
import com.yariksoffice.lingver.Lingver;

import java.util.Locale;

public class SettingsApp extends AppCompatActivity implements CallFragment, ListenerCallSounds {
    private ActivitySettingsAppBinding binding;
    private DialogFragmentProfile back;
    private DialogFragmentLanguage language;
    private AudioManager audioManager;
    private Sound sound = new Sound();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        LANGUAGES();
        JobServes();
        VOLUME();
        all();
    }

    private void all() {
        binding.card1.setOnClickListener(view -> {
            DialogFragmentSound dialogFragmentSound = DialogFragmentSound.newInstance(getResources().getString(R.string.sounds));
            dialogFragmentSound.show(getSupportFragmentManager(), "s5");
        });


        if (Locale.getDefault().getLanguage().equals("en"))
            binding.backIconS.setRotation(90);


        if (Sherdpreferanses.getInstance().GetTheme() == true)
            binding.switchDark.setChecked(true);
        else
            binding.switchDark.setChecked(false);


        binding.switchDark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int delayTime = 120;
                compoundButton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (b) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            Sherdpreferanses.getInstance().SetTheme(true);
                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            Sherdpreferanses.getInstance().SetTheme(false);
                        }
                    }
                }, delayTime);
            }
        });
    }


    private void VOLUME() {
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int cur = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        binding.seekBar.setMax(max);
        binding.seekBar.setProgress(cur);

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


    private void JobServes() {

        if (Sherdpreferanses.getInstance().GetNotify() == true) {
            binding.switch2.setChecked(true);
        } else {
            binding.switch2.setChecked(false);
        }

        binding.switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.switch2.setChecked(true);
                    Sherdpreferanses.getInstance().SetNotify(true);
                } else {
                    binding.switch2.setChecked(false);
                    Sherdpreferanses.getInstance().SetNotify(false);
                }
            }
        });
    }


    private void BUTTON_RESET() {
        binding.reset.setOnClickListener(view -> {
            back = DialogFragmentProfile.newInstance(getString(R.string.doyoudelete), getString(R.string.reset), getString(R.string.close));
            back.show(getSupportFragmentManager(), "reset");
            if (Sherdpreferanses.getInstance().GetSoundOther() == true)
                sound.S3();
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
            if (Sherdpreferanses.getInstance().GetSoundOther() == true)
                sound.S3();
        });
    }


    @Override
    public void call(boolean x) {
        if (x == true) {
            ViewModelGame model = new ViewModelProvider(this).get(ViewModelGame.class);
            Sherdpreferanses.getInstance().clear();
            startActivity(new Intent(SettingsApp.this, SplashScreenApp.class));
            ProfileEntity profile = new ProfileEntity(getString(R.string.player), getString(R.string.example), getString(R.string.birth), getString(R.string.male), getString(R.string.palestine));
            profile.setId(1);
            model.UpdateProfile(profile);

        } else {
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

    @Override
    public void sound1(boolean b) {
        if (b == true) {
            Sherdpreferanses.getInstance().SetSoundBackGrand(true);
            startService(new Intent(getApplicationContext(), ServiceSoundOnApp.class));
        } else {
            Sherdpreferanses.getInstance().SetSoundBackGrand(false);
            stopService(new Intent(getApplicationContext(), ServiceSoundOnApp.class));
        }
    }

    @Override
    public void sound2(boolean b) {
        if (b == true) {
            Sherdpreferanses.getInstance().SetSoundOther(true);
        } else {
            Sherdpreferanses.getInstance().SetSoundOther(false);
        }
    }

    @Override
    public void recreate() {
        finish();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        startActivity(getIntent());
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}