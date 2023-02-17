//package com.konden.projectpart2.test.table;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import java.util.List;
//
//@Dao
//public interface ProfileDao {
//
//    @Insert
//     void insertProfile(Profile... profile);
//
//    @Update
//    int updateProfile(Profile... profile);
//
//
//    @Query("select * from profile ")
//    LiveData<List<Profile>> getAllData ();
//
//    @Query("delete from profile")
//     void deleteProfile ();
//
//}
