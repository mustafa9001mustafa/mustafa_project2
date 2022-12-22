package com.konden.projectpart2.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.konden.projectpart2.room.game.DaoLevel;
import com.konden.projectpart2.room.game.LevelEntity;
import com.konden.projectpart2.room.profile.DaoProfile;
import com.konden.projectpart2.room.profile.ProfileEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ProfileEntity.class, LevelEntity.class}, version = 2, exportSchema = false)
public abstract class RoomDataBase extends RoomDatabase {

    public abstract DaoProfile doa();
    public abstract DaoLevel level();

    private static volatile RoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 6;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RoomDataBase.class, "word_database")
//                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
//
//    private static final RoomDatabase.Callback callback = new Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            databaseWriteExecutor.execute(() -> {
//                DaoProfile daoProfile = INSTANCE.doa();
//                ProfileEntity i = new ProfileEntity("player1", "example@example.com", "1/1/2000", "male", "palestine");
//                daoProfile.insertProfile(i);
//            });
//        }
//    };
}