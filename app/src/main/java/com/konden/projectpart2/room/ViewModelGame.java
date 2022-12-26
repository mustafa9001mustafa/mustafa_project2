package com.konden.projectpart2.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.konden.projectpart2.room.game.level.LevelEntity;
import com.konden.projectpart2.room.game.pattern.Pattern;
import com.konden.projectpart2.room.game.questios.QuestionsEntity;
import com.konden.projectpart2.room.profile.ProfileEntity;

import java.util.List;

public class ViewModelGame extends AndroidViewModel {

    private RepositoryGame repository;

    public ViewModelGame(@NonNull Application application) {
        super(application);
        repository = new RepositoryGame(application);
    }




    public void insertMobile(ProfileEntity profile) {

        repository.InsertProfiler(profile);
    }


    public void UpdateMobile(ProfileEntity profile) {

        repository.UpdateProfile(profile);
    }


    public void DeleteMobile(ProfileEntity profile) {

        repository.DeleteProfile(profile);
    }

    public LiveData<List<ProfileEntity>> getAllPlayer() {
        return repository.getAllProfile();
    }


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
}
