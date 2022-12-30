package com.konden.projectpart2.room.game.level;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface DaoLevel {
    @Insert
    void InsertLevel(LevelEntity entity);

    @Update
    void UpdateLevel(LevelEntity entity);

    @Delete
    void DeleteLevel(LevelEntity entity);

    @Query("select * from LevelEntity order by level_no asc")
    LiveData<List<LevelEntity>> GetAllLevel();

//    @Query("select level_no from levelentity  ")
//    LiveData<List<LevelEntity>> GetLevel();

}
