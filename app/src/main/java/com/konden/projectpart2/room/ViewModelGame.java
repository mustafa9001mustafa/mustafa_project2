package com.konden.projectpart2.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.konden.projectpart2.room.game.level.LevelEntity;
import com.konden.projectpart2.room.game.questios.QuestionsEntity;

import java.util.List;

public class ViewModelGame extends AndroidViewModel {

    private final RepositoryGame repository;

    public ViewModelGame(@NonNull Application application) {
        super(application);
        repository = new RepositoryGame(application);
    }

//    public void insertProfile(ProfileEntity profile) {
//
//        repository.InsertProfiler(profile);
//    }
//
//
//    public void UpdateProfile(ProfileEntity profile) {
//
//        repository.UpdateProfile(profile);
//    }
//
//
//    public void DeleteProfile(ProfileEntity profile) {
//
//        repository.DeleteProfile(profile);
//    }
//
//    public LiveData<List<ProfileEntity>> getAllPlayer() {
//        return repository.getAllProfile();
//    }
//

    public void InsertLevel(LevelEntity level) {

        repository.InsertLevel(level);
    }

    public LiveData<List<LevelEntity>> getAllLevel() {
        return repository.getAllLevel();
    }



    public void InsertQuestions(QuestionsEntity level) {

        repository.InsertQuestions(level);
    }

    public LiveData<List<QuestionsEntity>> getAllQuestions(int id) {
        return repository.getAllQuestions(id);
    }

    public void updateLevel_evolution(double level_evolution,int level_no) {
        repository.updateLevel_evolution(level_evolution,level_no);
    }

}
