package com.konden.projectpart2.room.game.level;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface DaoLevel {
    @Insert
    void InsertLevel(LevelEntity entity);

    @Query("select * from LevelEntity order by level_no asc")
    LiveData<List<LevelEntity>> GetAllLevel();


    @Query("UPDATE LevelEntity SET level_evolution = :level_evolution WHERE level_no = :standing_level_no")
    void updateLevel_evolution(double level_evolution,int standing_level_no);

}
