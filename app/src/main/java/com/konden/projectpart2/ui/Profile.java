package com.konden.projectpart2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import com.konden.projectpart2.R;
import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.databinding.ActivityProfileBinding;

import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;
import java.util.Locale;

public class Profile extends AppCompatActivity
//        implements CallProfileData
{
//    int id;
    private ActivityProfileBinding binding;
//    private ViewModelGame model;
//    private ProfileEntity profile;
//    private DialogFragmentProfile dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fullScreen();

        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        model = new ViewModelProvider(this).get(ViewModelGame.class);

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
    private void ALL_METHOD() {
        ANIMATION_ALL();
        ON_BACK_UI();
        GET_POINT();
        GET_WIN();
        GET_LOSER();
        GET_LEVEL_FINISHED();
        GET_KD();
        GETROTECION();
//        GET_USER();
//        EDIT_BUTTON();
    }

    private void GETROTECION() {
        if (Locale.getDefault().getLanguage().equals("en"))
            binding.backIcon.setRotation(90);
    }


    private void GET_POINT() {
        binding.point.setText(String.valueOf(Sherdpreferanses.getInstance().getScore()));
    }

    private void GET_WIN() {
        binding.levelWin.setText(String.valueOf(Sherdpreferanses.getInstance().getWin()));
    }

    private void GET_LOSER() {
        binding.levelLoser.setText(String.valueOf(Sherdpreferanses.getInstance().getLoser()));
    }

    private void GET_LEVEL_FINISHED() {
        binding.finishedLevel.setText(String.valueOf(Sherdpreferanses.getInstance().getFinished()));
    }

    private void GET_KD() {
        float x = Sherdpreferanses.getInstance().SetKD(Sherdpreferanses.getInstance().getWin(), Sherdpreferanses.getInstance().getLoser());
        binding.kd.setText(String.valueOf(x));
    }

    private void ON_BACK_UI() {
        binding.backIcon.setOnClickListener(view -> {
            startActivity(new Intent(Profile.this, SettingsApp.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

//    private void GET_USER() {
//        model.getAllPlayer().observe(this, new Observer<List<ProfileEntity>>() {
//            @Override
//            public void onChanged(List<ProfileEntity> profileEntities) {
//                profile = profileEntities.get(0);
//                id = profile.getId();
//                binding.usernameText.setText(profile.getUsername());
//                binding.emailText.setText(profile.getEmail());
//                binding.birthdayText.setText(profile.getBirthday());
//                binding.genderText.setText(profile.getGender());
//                binding.countryText.setText(profile.getCountry());
//            }
//        });
//    }

//    private void EDIT_BUTTON() {
//        binding.editIcon.setOnClickListener(view -> {
//            dialogFragment = DialogFragmentProfile.newInstance(profile.getUsername(), profile.getEmail(), profile.getBirthday(), profile.getGender(), profile.getCountry());
//            dialogFragment.show(getSupportFragmentManager(), "d");
//
//
//        });
//    }

    private void ANIMATION_ALL() {
        AnimationAll all = new AnimationAll();
//        binding.cardProfile.setAnimation(all.a1_FromTheTop(Profile.this));
        binding.cardGame.setAnimation(all.a1_FromTheTop(Profile.this));
        binding.v1.setAnimation(all.a3_FromTheLeft(Profile.this));
        binding.v2.setAnimation(all.a2_FromTheRight(Profile.this));
//        binding.v3.setAnimation(all.a3_FromTheLeft(Profile.this));
//        binding.v4.setAnimation(all.a2_FromTheRight(Profile.this));
        binding.backIcon.setAnimation(new AnimationAll().a2_FromTheRight(Profile.this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Profile.this, SettingsApp.class));
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

//    @Override
//    public void onProfile(String user, String email, String birth, String gender, String contre, int x) {
//        if (x == 9) {
//            profile = new ProfileEntity(user, email, birth, gender, contre);
//            profile.setId(id);
//            model.UpdateProfile(profile);
//            dialogFragment.dismiss();
//        }
//    }
}