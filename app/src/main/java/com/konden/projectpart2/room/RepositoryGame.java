package com.konden.projectpart2.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.konden.projectpart2.room.game.level.DaoLevel;
import com.konden.projectpart2.room.game.level.LevelEntity;
import com.konden.projectpart2.room.game.questios.DaoQuestions;
import com.konden.projectpart2.room.game.questios.QuestionsEntity;
import com.konden.projectpart2.room.profile.DaoProfile;
import com.konden.projectpart2.room.profile.ProfileEntity;

import java.util.List;

public class RepositoryGame {
    private final DaoProfile daoProfile;
    private final DaoLevel doaLevel;
    private final DaoQuestions daoQuestions;

    RepositoryGame(Application application) {
        RoomDataBase db = RoomDataBase.getDatabase(application);
        daoProfile = db.doa();
        doaLevel = db.level();
        daoQuestions = db.questions();
    }

    void InsertProfiler(ProfileEntity entity) {
        RoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoProfile.insertProfile
                                (entity);
            }
        });
    }


    void UpdateProfile(ProfileEntity entity) {
        RoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoProfile.UpdateProfile(entity);

            }
        });
    }

    void DeleteProfile(ProfileEntity entity) {
        RoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoProfile.DeleteProfile(entity);
            }
        });
    }

    LiveData<List<ProfileEntity>> getAllProfile() {
        return daoProfile.GetAllPROFILE();
    }



    void InsertLevel(LevelEntity level) {
        RoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                doaLevel.InsertLevel(level);
            }
        });
    }


    LiveData<List<LevelEntity>> getAllLevel() {
        return doaLevel.GetAllLevel();
    }


    void InsertQuestions(QuestionsEntity questions) {
        RoomDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoQuestions.InsertQuestions(questions);
            }
        });
    }

    LiveData<List<QuestionsEntity>> getAllQuestions(int id) {
        return daoQuestions.GetAllQuestions(id);
    }




}
