package com.konden.projectpart2.room.game.questios;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface DaoQuestions {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void InsertQuestions(QuestionsEntity entity);

    @Update
    void UpdateQuestions(QuestionsEntity entity);

    @Delete
    void DeleteQuestions(QuestionsEntity entity);

    // للبحث
    @Query("select * from QuestionsEntity Where levelNoChild=:LevelNo" +
            " order by id_Question asc")
    LiveData<List<QuestionsEntity>> GetAllQuestions(int LevelNo);
}
