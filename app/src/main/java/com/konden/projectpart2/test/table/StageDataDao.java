//package com.konden.projectpart2.test.table;
//
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.Query;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Dao
//public interface StageDataDao {
//
//    @Insert
//    public void insertStageData (ArrayList<StagesData> stagesData);
//
//    @Query("select * from StagesData order by numberLavel")
//    LiveData<List<StagesData>> getAllStageData ();
//
//    @Query("select * from StagesData order by numberLavel")
//    LiveData<StagesData> getPoints ();
//
//}
