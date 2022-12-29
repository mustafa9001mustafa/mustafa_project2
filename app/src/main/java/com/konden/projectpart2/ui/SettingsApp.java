package com.konden.projectpart2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.R;
import com.konden.projectpart2.constant.FinalContract;
import com.konden.projectpart2.databinding.ActivitySettingsAppBinding;
import com.konden.projectpart2.fragments.fragment_setting.DialogFragmentBack;
import com.konden.projectpart2.fragments.fragment_setting.DialogFragmentLanguage;
import com.konden.projectpart2.interfases.CallFragment;
import com.konden.projectpart2.jopservies.MyServices;
import com.konden.projectpart2.jopservies.ServiceSoundOnApp;
import com.konden.projectpart2.myapplication.MyApp;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;
import com.yariksoffice.lingver.Lingver;
import java.util.Locale;

public class SettingsApp extends AppCompatActivity implements CallFragment {
    private ActivitySettingsAppBinding binding;
    private DialogFragmentBack back;
    private DialogFragmentLanguage language;
    private JobInfo info;
    private JobScheduler scheduler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        CheckMode();
        binding.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b == true){
                    Sherdpreferanses.getInstance().SetSound(b);
                    startService(new Intent(getApplicationContext(), ServiceSoundOnApp.class));
                }else {
                    stopService(new Intent(getApplicationContext(), ServiceSoundOnApp.class));
                    Sherdpreferanses.getInstance().SetSound(b);

                }
            }
        });

        if (Sherdpreferanses.getInstance().GetSound() == true){
            binding.switch1.setChecked(true);
        }else {
            binding.switch1.setChecked(false);
        }

        if (Sherdpreferanses.getInstance().GetNotify() == true){
            binding.switch2.setChecked(true);
        }else {
            binding.switch2.setChecked(false);
        }



        if (Locale.getDefault().getLanguage().equals("en"))
            binding.backIconS.setRotation(90);


        binding.switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Sherdpreferanses.getInstance().SetTheme(isChecked);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartActivity();
                } else {
                    Sherdpreferanses.getInstance().SetTheme(isChecked);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartActivity();
                }
            }
        });

    }

    private void CheckMode() {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            binding.switch3.setChecked(true);
            Sherdpreferanses.getInstance().SetTheme(true);
        } else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            binding.switch3.setChecked(false);
            Sherdpreferanses.getInstance().SetTheme(false);
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
        LANGUAGES();
        JobServes();
    }


    private void JobServes() {
        binding.switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Sherdpreferanses.getInstance().SetNotify(b);
                    ComponentName name = new ComponentName(getBaseContext(), MyServices.class);
                    info = new JobInfo.Builder(FinalContract.Job_id, name)
                            .setPeriodic((24*60*60*1000),
                                    JobInfo.getMinFlexMillis())
                            .build();
                    scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                    scheduler.schedule(info);
                    Toast.makeText(SettingsApp.this, "تم تشغيل الإشعارات", Toast.LENGTH_SHORT).show();
                } else {
                    Sherdpreferanses.getInstance().SetNotify(b);
                    scheduler.cancel(FinalContract.Job_id);
                    Toast.makeText(SettingsApp.this, "تم إيقاف الإشعارات", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    private void restartActivity() {

        Intent intent = new Intent(getApplicationContext(), SettingsApp.class);
        startActivity(intent);
    }
}