package com.konden.projectpart2.room;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.konden.projectpart2.appcontroller.AppControllers;
import com.konden.projectpart2.room.game.level.DaoLevel;
import com.konden.projectpart2.room.game.level.LevelEntity;
import com.konden.projectpart2.room.game.questios.DaoQuestions;
import com.konden.projectpart2.room.game.questios.QuestionsEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// the version 4 is test
@Database(entities = {
//        ProfileEntity.class,
        LevelEntity.class, QuestionsEntity.class}, version = 4, exportSchema = false)
public abstract class RoomDataBase extends RoomDatabase {


//    public abstract DaoProfile doa();

    private static final int NUMBER_OF_THREADS = 8;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile RoomDataBase INSTANCE;
    private static JSONObject object;
    private static final Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                GetData_Level_IS_FIRST();
                GetData_QuestionsIS_FIRST();
            });
        }
    };

    static RoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RoomDataBase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static void GetData_Level_IS_FIRST() {

        DaoLevel daoLevel;
        daoLevel = RoomDataBase.INSTANCE.DoaLevel();
        Log.e(TAG, "GetData_Level: 1");
        try {

            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            Log.e(TAG, "GetData_Level: 2");
            for (int i = 0; i < jsonArray.length(); i++) {
                object = jsonArray.getJSONObject(i);
                Log.e(TAG, "GetData_Level: 3");
                int level_no = object.getInt("level_no");
//                Log.e(TAG, "getAllData: level_no " + level_no);
                int unlock_points = object.getInt("unlock_points");
                Log.e(TAG, "GetData_Level: 4" + level_no);
                LevelEntity level = new LevelEntity(level_no, unlock_points);

                daoLevel.InsertLevel(level);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "getAllData: error");
        }

    }


    private static void GetData_QuestionsIS_FIRST() {
        DaoQuestions daoQuestions;
        daoQuestions = RoomDataBase.INSTANCE.questions();

        Log.e(TAG, "GetData_Questions: 1");
        try {
            Log.e(TAG, "GetData_Questions: 6");
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < jsonArray.length(); i++) {
                object = jsonArray.getJSONObject(i);
                Log.e(TAG, "GetData_Questions: 7");

                int level_no = object.getInt("level_no");
                JSONArray jr = object.getJSONArray("questions");
                Log.e(TAG, "GetData_Questions: 8" + level_no);

                for (int j = 0; j < jr.length(); j++) {
                    object = jr.getJSONObject(j);
                    int id = object.getInt("id");
                    String title = object.getString("title");
                    String answer_1 = object.getString("answer_1");
                    String answer_2 = object.getString("answer_2");
                    String answer_3 = object.getString("answer_3");
                    Log.e(TAG, "GetData_Questions: 9");

                    String answer_4 = object.getString("answer_4");
                    String true_answer = object.getString("true_answer");
                    int points = object.getInt("points");
                    int duration = object.getInt("duration");
                    String hint = object.getString("hint");

                    JSONObject object2 = object.getJSONObject("pattern");

                    int pattern_id = object2.getInt("pattern_id");
                    Log.e(TAG, "GetData_Questions: 10");


                    QuestionsEntity questions = new QuestionsEntity(id, title, answer_1, answer_2, answer_3, answer_4, true_answer, points, duration, hint, level_no
                            , pattern_id);
                    daoQuestions.InsertQuestions(questions);
                    Log.e(TAG, "GetData_Questions: -2");
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static String loadJSONFromAsset() {
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

    public abstract DaoLevel DoaLevel();

    public abstract DaoQuestions questions();

}