package com.konden.projectpart2.ui;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.R;
import com.konden.projectpart2.databinding.ActivityMainBinding;
import com.konden.projectpart2.fragments.fragment_setting.DialogFragmentBack;
import com.konden.projectpart2.interfases.CallFragment;
import com.konden.projectpart2.room.ViewModelGame;
import com.konden.projectpart2.room.game.LevelEntity;
import com.konden.projectpart2.room.game.QuestionsEntity;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements CallFragment {
    private ActivityMainBinding binding;
    DialogFragmentBack back;
    private ViewModelGame viewModel;
    LevelEntity level;
    QuestionsEntity questions;
    JSONObject object, object2;
    JSONArray jr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(ViewModelGame.class);

        if (Sherdpreferanses.getInstance().isFirstTimeGame()) {
            getAllData();
        }

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

        });
    }

    private void SETTING_BUTTON() {
        binding.setting.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SettingsApp.class));
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);

        });
    }

    private void EXIT_BUTTON() {
        binding.exit.setOnClickListener(view -> {
            ONS_HOW_DIALOG();
        });
    }

    private void ANIMATIONS() {

        AnimationAll all = new AnimationAll();
        binding.setting.setAnimation(all.a1_FromTheTop(MainActivity.this));
        binding.start.setAnimation(all.a1_FromTheTop(MainActivity.this));
        binding.exit.setAnimation(all.a1_FromTheTop(MainActivity.this));
        binding.logoMain.setAnimation(all.a9_Small_to_big(MainActivity.this));

    }


    //    Json  وارساله كملف Json  الوصول الى ملف ال
    //encoding : convert Json To String

    private void getAllData() {

        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < jsonArray.length(); i++) {
                object = jsonArray.getJSONObject(i);
                int level_no = object.getInt("level_no");
                Log.e(TAG, "getAllData: level_no " + level_no);
                int unlock_points = object.getInt("unlock_points");
                Log.e(TAG, "getAllData: unlock_points " + unlock_points);

                level = new LevelEntity(level_no, unlock_points);
                viewModel.InsertLevel(level);

                jr = object.getJSONArray("questions");

                for (int j = 0; j < jr.length(); j++) {
                    object = jr.getJSONObject(j);
                    String title = object.getString("title");
                    String answer_1 = object.getString("answer_1");
                    String answer_2 = object.getString("answer_2");
                    String answer_3 = object.getString("answer_3");
                    String answer_4 = object.getString("answer_4");
                    String true_answer = object.getString("true_answer");
                    int points = object.getInt("points");
                    int duration = object.getInt("duration");
                    String hint = object.getString("hint");

                    questions = new QuestionsEntity(title, answer_1, answer_2, answer_3, answer_4, true_answer, points, duration, hint);
                    object2 = object.getJSONObject("pattern");

                    String pattern_id = object2.getString("pattern_id");
                    String pattern_name = object2.getString("pattern_name");
                    Log.e(TAG, "getAllData: " + title);

                }

//                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "getAllData: ssssssssssssssssssssssssss");
        }
    }

    public String loadJSONFromAsset() {
        String json;

        try {
            InputStream is = getBaseContext().getAssets().open("puzzleGameData.json"); //تقوم بجلب ملف الجيسن
            int size = is.available(); //بتجبلك البايتات فيه كم حجمها
            byte[] buffer = new byte[size];
            //Stream :  assets  فتح قناه ما بين الكلاس الخاص فيا وما بين ملف ال
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8); // convert byte to String
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void ONS_HOW_DIALOG() {
        back = DialogFragmentBack.newInstance(getString(R.string.youknow), getString(R.string.Exit), getString(R.string.close));
        back.show(getSupportFragmentManager(), "you");
    }

    @Override
    public void onBackPressed() {
        ONS_HOW_DIALOG();
    }

    @Override
    public void call(boolean x) {
        if (x == true) {
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