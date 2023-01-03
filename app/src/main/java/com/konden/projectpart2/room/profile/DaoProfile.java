package com.konden.projectpart2.room.profile;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoProfile {

    @Insert
    long insertProfile(ProfileEntity entity);

    @Update
    void UpdateProfile(ProfileEntity entity);
    //todo
    @Delete
    void DeleteProfile(ProfileEntity entity);

    @Query("Select * from ProfileEntity")
    LiveData<List<ProfileEntity>> GetAllPROFILE();
}