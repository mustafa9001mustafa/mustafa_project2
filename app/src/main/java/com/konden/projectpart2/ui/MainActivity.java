package com.konden.projectpart2.ui;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.konden.projectpart2.R;
import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.appcontroller.AppControllers;
import com.konden.projectpart2.databinding.ActivityMainBinding;
import com.konden.projectpart2.fragments.fragment_setting.DialogFragmentBackOrReset;
import com.konden.projectpart2.fragments.fragment_shop.ShopFragment;
import com.konden.projectpart2.fragments.spin_dialog.SpinFragment;
import com.konden.projectpart2.interfases.number_image.CallImageNumber;
import com.konden.projectpart2.interfases.settings.CallFragment;
import com.konden.projectpart2.room.ViewModelGame;
import com.konden.projectpart2.room.game.level.LevelEntity;
import com.konden.projectpart2.room.game.questios.QuestionsEntity;
import com.konden.projectpart2.sherdpreferanses.Sherdpreferanses;
import com.konden.projectpart2.sound.Sound;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class MainActivity extends AppCompatActivity implements CallImageNumber, CallFragment {
    private ActivityMainBinding binding;
    private SpinFragment spinFragment;
    private ShopFragment shopFragment;
    private JSONObject object;
    private ViewModelGame viewModel;
    private DialogFragmentBackOrReset back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fullScreen();
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ViewModelGame.class);


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
        START_BUTTON();
        SETTING_BUTTON();
        ANIMATIONS();
        F_BUTTON();
        SHOW_DIALOG();
        SHOP_CLICK();
        BACK_GROUND_ACTIVITY();
        Check_data_add();
    }

    private void BACK_GROUND_ACTIVITY() {
        if (Sherdpreferanses.getInstance().GetGameImageNumber() == 1)
            binding.getRoot().setBackgroundResource(R.drawable.game_q);
        else if (Sherdpreferanses.getInstance().GetGameImageNumber() == 2)
            binding.getRoot().setBackgroundResource(R.drawable.back_geme_2);
        else if (Sherdpreferanses.getInstance().GetGameImageNumber() == 3)
            binding.getRoot().setBackgroundResource(R.drawable.back_game_3);
    }

    private void SHOP_CLICK() {
        binding.conShop.setOnClickListener(view -> {
            shopFragment = ShopFragment.newInstance();
            shopFragment.show(getSupportFragmentManager(), "t");
        });
    }

    private void SHOW_DIALOG() {
        binding.spinLayot.setOnClickListener(view -> {
            spinFragment = SpinFragment.newInstance();
            spinFragment.show(getSupportFragmentManager(), "s");
        });


    }

    private void F_BUTTON() {
        binding.start.setButtonColor(getColor(R.color.green));
    }

    private void START_BUTTON() {
        binding.start.setOnClickListener(view -> {

            startActivity(new Intent(MainActivity.this, StartPlaying.class));
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            Sherdpreferanses.getInstance().SetScore(3000);

            if (Sherdpreferanses.getInstance().GetSoundOther())
                Sound.getInstance().S3();

        });
    }

    private void SETTING_BUTTON() {
        binding.conSetting.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SettingsApp.class));
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            if (Sherdpreferanses.getInstance().GetSoundOther())
                Sound.getInstance().S3();
        });
    }

    private void ANIMATIONS() {

        AnimationAll all = new AnimationAll();
        binding.conSetting.setAnimation(all.a1_FromTheTop(MainActivity.this));
        binding.conShop.setAnimation(all.a1_FromTheTop(MainActivity.this));
        binding.start.setAnimation(all.a4_FromTheBottom(MainActivity.this));
        binding.spinLayot.setAnimation(all.a9_Small_to_big(MainActivity.this));
    }

    private void Check_data_add() {
        if (Sherdpreferanses.getInstance().isNotFirstGameCheck()) {
            viewModel.getAllLevel().observe(this, new Observer<List<LevelEntity>>() {
                @Override
                public void onChanged(List<LevelEntity> levelEntities) {
                    Log.e("TAG", "onCreate: " + levelEntities.size());
                    try {
                        JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            object = jsonArray.getJSONObject(i);
                            int level_no = object.getInt("level_no");
                            Log.e(TAG, "onChanged: 333");
                            if (levelEntities.size() <= level_no) {
                                Log.e(TAG, "onChanged: level number " + level_no);
                                GetData(levelEntities.size());
//                                GetData_Questions(levelEntities.size());

                            } else
                                Log.e(TAG, "onChanged: not ok");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(TAG, "getAllData: error");
                    }
                }
            });

        }
    }

    private void GetData(int get_level_size) {
        Log.e(TAG, "GetData_Level: 1");
        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            Log.e(TAG, "GetData_Level: 2");
            for (int i = 0; i < jsonArray.length(); i++) {
                object = jsonArray.getJSONObject(i);
                int level_no = object.getInt("level_no");
                JSONArray jr = object.getJSONArray("questions");
                Log.e(TAG, "getAllData: level_no " + level_no);
                int unlock_points = object.getInt("unlock_points");
                if (level_no > get_level_size) {
                    LevelEntity level = new LevelEntity(level_no, unlock_points);
                    viewModel.InsertLevel(level);
                    Log.e(TAG, "GetData_Level: -1");


                    for (int j = 0; j < jr.length(); j++) {
                        object = jr.getJSONObject(j);
                        int id = object.getInt("id");
                        String title = object.getString("title");
                        String answer_1 = object.getString("answer_1");
                        String answer_2 = object.getString("answer_2");
                        String answer_3 = object.getString("answer_3");
                        String answer_4 = object.getString("answer_4");
                        String true_answer = object.getString("true_answer");
                        int points = object.getInt("points");
                        int duration = object.getInt("duration");
                        String hint = object.getString("hint");
                        JSONObject object2 = object.getJSONObject("pattern");
                        int pattern_id = object2.getInt("pattern_id");
                        if (level_no > get_level_size) {
                            QuestionsEntity questions = new QuestionsEntity(id, title, answer_1, answer_2, answer_3, answer_4, true_answer, points, duration, hint, level_no
                                    , pattern_id);
                            viewModel.InsertQuestions(questions);
                            Log.e(TAG, "GetData_Questions: -1");
                        }

                    }

                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "getAllData: error");
        }
    }

//    private void GetData_Questions(int get_level_size) {
//        try {
//            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
//            for (int i = 0; i < jsonArray.length(); i++) {
//                object = jsonArray.getJSONObject(i);
//                int level_no = object.getInt("level_no");
//                JSONArray jr = object.getJSONArray("questions");
//                Log.e(TAG, "GetData_Questions: " + level_no);
//
//                for (int j = 0; j < jr.length(); j++) {
//                    object = jr.getJSONObject(j);
//                    int id = object.getInt("id");
//                    String title = object.getString("title");
//                    String answer_1 = object.getString("answer_1");
//                    String answer_2 = object.getString("answer_2");
//                    String answer_3 = object.getString("answer_3");
//                    String answer_4 = object.getString("answer_4");
//                    String true_answer = object.getString("true_answer");
//                    int points = object.getInt("points");
//                    int duration = object.getInt("duration");
//                    String hint = object.getString("hint");
//                    JSONObject object2 = object.getJSONObject("pattern");
//                    int pattern_id = object2.getInt("pattern_id");
//                    if (level_no > get_level_size) {
//                        QuestionsEntity questions = new QuestionsEntity(id, title, answer_1, answer_2, answer_3, answer_4, true_answer, points, duration, hint, level_no
//                                , pattern_id);
//                        viewModel.InsertQuestions(questions);
//                        Log.e(TAG, "GetData_Questions: -1");
//                    }
//
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = AppControllers.getInstance().getBaseContext().getAssets().open("puzzleGameData.json"); //تقوم بجلب ملف الجيسن
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

    //    Json  وارساله كملف Json  الوصول الى ملف ال
    //encoding : convert Json To String

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void ONS_HOW_DIALOG() {
        back = DialogFragmentBackOrReset.newInstance(getString(R.string.youknow), getString(R.string.Exit), getString(R.string.close));
        back.show(getSupportFragmentManager(), "you");

    }

    @Override
    public void onBackPressed() {
        ONS_HOW_DIALOG();
    }

//    @Override
//    public void call(boolean x) {
//        Intent intent = new Intent(getApplicationContext(), ServiceSoundOnApp.class);
//        if (x == true) {
//            stopService(intent);
//            finish();
//        } else if (x == false) {
//            back.dismiss();
//        }
//    }
//
//    @Override
//    public void callLanguage(boolean b) {
//
//    }
//
//    @Override
//    public void callLanguageClose(boolean b) {
//
//    }

    @Override
    public void image_number() {
        shopFragment.dismiss();
        BACK_GROUND_ACTIVITY();
    }

    @Override
    public void call(boolean x) {
        if (x)
            finish();
        else
            back.dismiss();
    }

    @Override
    public void callLanguage(boolean b) {

    }

    @Override
    public void callLanguageClose(boolean b) {

    }
}