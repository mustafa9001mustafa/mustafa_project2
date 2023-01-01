package com.konden.projectpart2.ui;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.konden.projectpart2.R;
import com.konden.projectpart2.animations.AnimationAll;
import com.konden.projectpart2.constant.FinalContract;
import com.konden.projectpart2.databinding.ActivitySplachScreenBinding;
import com.konden.projectpart2.jopservies.MyServices;
import com.konden.projectpart2.jopservies.ServiceSoundOnApp;
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
    private ActivitySplachScreenBinding binding;
    private int progressStatus = 0, sleep = 16;
    private ViewModelGame viewModel;
    private JSONObject object;

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
        CheckDataInsert();
        NOTIFICATION_SWISHIEST();
    }

    private void NOTIFICATION_SWISHIEST() {

        ComponentName name = new ComponentName(getBaseContext(), MyServices.class);
        JobInfo info = new JobInfo.Builder(FinalContract.Job_id, name)
                .setPeriodic((24 * 60 * 60 * 1000),
                        JobInfo.getMinFlexMillis())
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

        if (Sherdpreferanses.getInstance().GetNotify() == true) {
            if (Sherdpreferanses.getInstance().isNotFirstMainGame2()) {
                scheduler.cancel(FinalContract.Job_id);
            }
            scheduler.schedule(info);
        } else {
            scheduler.cancel(FinalContract.Job_id);

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
            Sherdpreferanses.getInstance().SetUnlockNow(2);
            Sherdpreferanses.getInstance().SetFinished(0);
            Sherdpreferanses.getInstance().SetWin(0);
            Sherdpreferanses.getInstance().SetLoser(0);
            Sherdpreferanses.getInstance().SetScore(2);
            Sherdpreferanses.getInstance().SetSoundOther(true);
            Sherdpreferanses.getInstance().SetSoundBackGrand(true);
            Sherdpreferanses.getInstance().SetCheckBox(true);

        }
    }

    private void ANIMATIONS() {
        AnimationAll all = new AnimationAll();
        binding.sp.setAnimation(all.a9_Small_to_big(SplashScreenApp.this));
        binding.nameApp.setAnimation(all.a4_FromTheBottom(SplashScreenApp.this));
        binding.progressBar.setAnimation(all.a4_FromTheBottom(SplashScreenApp.this));
    }


    private void TIMER_SPLASH() {

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += FinalContract.PLUS_PROGRESS;
                    binding.progressBar.setProgress(progressStatus);

                    if (progressStatus == 100) {
                        Intent intent = new Intent(new Intent(getApplicationContext(), MainActivity.class));
                        startActivity(intent);
                        CHECKSERVES();
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

    private void CHECKSERVES() {
        Intent intent1 = new Intent(getApplicationContext(), ServiceSoundOnApp.class);
        if (Sherdpreferanses.getInstance().GetSoundBackGrand() == false)
            stopService(new Intent(getApplicationContext(), MainActivity.class));
        else
            startService(intent1);

        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
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
                LevelEntity level = new LevelEntity(level_no, unlock_points);
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
                JSONArray jr = object.getJSONArray("questions");

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
                    QuestionsEntity questions = new QuestionsEntity(id, title, answer_1, answer_2, answer_3, answer_4, true_answer, points, duration, hint, level_no
                            , pattern_id
                    );
                    viewModel.InsertQuestions(questions);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
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