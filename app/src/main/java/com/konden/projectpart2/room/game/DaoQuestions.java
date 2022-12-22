package com.konden.projectpart2.room.game;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoQuestions {
    @Insert
    void insertQuestions(LevelEntity entity);

    @Update
    void UpdateQuestions(LevelEntity entity);

    @Delete
    void DeleteQuestions(LevelEntity entity);

    @Query("select * from QuestionsEntity")
    LiveData<List<QuestionsEntity>> GetAllQuestions();
}
