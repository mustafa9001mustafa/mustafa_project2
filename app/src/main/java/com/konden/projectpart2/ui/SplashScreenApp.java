package com.konden.projectpart2.ui;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.konden.projectpart2.R;
import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.constant.FinalContract;
import com.konden.projectpart2.databinding.ActivitySplachScreenBinding;
import com.konden.projectpart2.jopservies.MyServices;
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


public class SplashScreenApp extends AppCompatActivity {
    ActivitySplachScreenBinding binding;
    private int progressStatus = 0, sleep = 17, i = 1;
    private ViewModelGame viewModel;
    private LevelEntity level;
    private QuestionsEntity questions;
    private JSONObject object, object2;
    private JSONArray jr;
    private JobInfo info;
    private JobScheduler scheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Sherdpreferanses.getInstance().GetTheme() == true) {
            setTheme(R.style.Theme_ProjectPart2_dark);
            Log.e(TAG, "onCreate: 12412421412");
        } else {
            setTheme(R.style.Theme_ProjectPart2);
            Log.e(TAG, "onCreate: segsegseg");
        }
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
        CheckDataInsert();
        NOTIFICATION_SWISHIEST();
    }

    private void NOTIFICATION_SWISHIEST() {
        if (Sherdpreferanses.getInstance().GetNotify() == true) {
            ComponentName name = new ComponentName(getBaseContext(), MyServices.class);
            info = new JobInfo.Builder(FinalContract.Job_id, name)
                    .setPeriodic((24 * 60 * 60 * 1000),
                            JobInfo.getMinFlexMillis())
                    .build();
            scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            scheduler.schedule(info);
            Toast.makeText(SplashScreenApp.this, "تم تشغيل الإشعارات", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "NOTIFICATION_SWISHIEST: ok");
        } else {
            if (Sherdpreferanses.getInstance().isNotFirstMainGame2()) {
                scheduler.cancel(FinalContract.Job_id);
                Toast.makeText(SplashScreenApp.this, "تم إيقاف الإشعارات", Toast.LENGTH_SHORT).show();
            }
            Log.e(TAG, "NOTIFICATION_SWISHIEST: no");


        }

    }

    private void CheckDataInsert() {
        viewModel = new ViewModelProvider(this).get(ViewModelGame.class);
        if (Sherdpreferanses.getInstance().isFirstTimeGame()) {
            GetData_Level();
            GetData_Questions();

        }

        if (Sherdpreferanses.getInstance().isFirstTimeOther()) {
            sleep = 41;
            Sherdpreferanses.getInstance().SetFinished(0);
            Sherdpreferanses.getInstance().SetWin(0);
            Sherdpreferanses.getInstance().SetLoser(0);
            Sherdpreferanses.getInstance().SetScore(2);
            Sherdpreferanses.getInstance().SetSoundOther(true);
            Sherdpreferanses.getInstance().SetSoundBackGrand(true);
        }
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
                    progressStatus += i;
                    binding.progressBar.setProgress(progressStatus);

                    if (progressStatus == 100)
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);

                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    //    Json  وارساله كملف Json  الوصول الى ملف ال
    //encoding : convert Json To String

    private void GetData_Level() {

        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < jsonArray.length(); i++) {
                object = jsonArray.getJSONObject(i);
                int level_no = object.getInt("level_no");
                Log.e(TAG, "getAllData: level_no " + level_no);
                int unlock_points = object.getInt("unlock_points");
                level = new LevelEntity(level_no, unlock_points);
                viewModel.InsertLevel(level);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "getAllData: error");
        }
    }

    private void GetData_Questions() {

        try {
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < jsonArray.length(); i++) {
                object = jsonArray.getJSONObject(i);
                int level_no = object.getInt("level_no");
                jr = object.getJSONArray("questions");

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

                    object2 = object.getJSONObject("pattern");

                    int pattern_id = object2.getInt("pattern_id");
                    questions = new QuestionsEntity(id, title, answer_1, answer_2, answer_3, answer_4, true_answer, points, duration, hint, level_no
                            , pattern_id
                    );
                    viewModel.InsertQuestions(questions);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "getAllData: error");
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

    @Override
    public void onBackPressed() {
    }
}