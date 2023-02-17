//package com.konden.projectpart2.test.table;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.sqlite.db.SupportSQLiteDatabase;
//
//import com.konden.projectpart2.appcontroller.AppControllers;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Database(entities = {StagesData.class, PuzzleData.class, Profile.class}, version = 2, exportSchema = false)
//public abstract class DataBacee extends RoomDatabase {
//
//    private static final int NUMBER_OF_THREADS = 4;
//    static final ExecutorService databaseWriteExecutor =
//            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
//    private static volatile DataBacee INSTANCE;
//
//
//    static DataBacee getDatabase(final Context context) {
//        if (INSTANCE == null) {
//            synchronized (DataBacee.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            DataBacee.class, "Game_database").fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback).build();
//
//                }
//            }
//        }
//        return INSTANCE;
//    }
//
//    private static final Callback sRoomDatabaseCallback = new Callback() {
//        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        ProfileDao userDao;
//        Date date = new Date();
//
//
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//
//
//            databaseWriteExecutor.execute(() -> {
//
//                userDao = DataBacee.INSTANCE.profileDao();
//                Profile user = new Profile("name", "" + "user@gmail.com", formatter.format(date), "Male", "palestine");
//                userDao.insertProfile(user);
//                getData();
//
//            });
//        }
//    };
//
//
//
//    public static String loadJSONFromAsset() {
//        String json = null;
//        try {
//            InputStream is = AppControllers.getInstance().getAssets().open("puzzleGameData.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, StandardCharsets.UTF_8);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }
//
//    public static void getData() {
//        try {
//
//            StageDataDao levelDao;
//            PuzzleDataDao puzzleDao;
//
//            ArrayList<PuzzleData> puzzles = new ArrayList<>();
//            ArrayList<StagesData> levels = new ArrayList<>();
//            levelDao = DataBacee.INSTANCE.stageDataDao();
//            puzzleDao = DataBacee.INSTANCE.puzzleDataDao();
//
//            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());
//
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                int levelNo = jsonObject.getInt("level_no");
//                int unlockPoints = jsonObject.getInt("unlock_points");
//
//                StagesData level = new StagesData(levelNo, unlockPoints);
//                levels.add(level);
//
//
//                JSONArray array = jsonObject.getJSONArray("questions");
//                for (int n = 0; n < array.length(); n++) {
//                    JSONObject jsonObject1 = array.getJSONObject(n);
//
//                    int id = jsonObject1.getInt("id");
//                    String title = jsonObject1.getString("title");
//                    String answer1 = jsonObject1.getString("answer_1");
//                    String answer2 = jsonObject1.getString("answer_2");
//                    String answer3 = jsonObject1.getString("answer_3");
//                    String answer4 = jsonObject1.getString("answer_4");
//                    String true_answer = jsonObject1.getString("true_answer");
//                    int points = jsonObject1.getInt("points");
//                    int duration = jsonObject1.getInt("duration");
//                    String hint = jsonObject1.getString("hint");
//
//
//                    JSONObject jsonObject2 = jsonObject1.getJSONObject("pattern");
//                    int pattern_id = jsonObject2.getInt("pattern_id");
//                    String pattern_name = jsonObject2.getString("pattern_name");
//
//
//                    PuzzleData puzzle = new PuzzleData(id, title, answer1, answer2, answer3, answer4, true_answer, points, levelNo, duration, hint, pattern_id, pattern_name);
//                    puzzles.add(puzzle);
//                }
//
//            }
//
//            levelDao.insertStageData(levels);
//            puzzleDao.insertPuzzleData(puzzles);
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public abstract ProfileDao profileDao();
//
//    public abstract StageDataDao stageDataDao();
//
//    public abstract PuzzleDataDao puzzleDataDao();
//
//}
