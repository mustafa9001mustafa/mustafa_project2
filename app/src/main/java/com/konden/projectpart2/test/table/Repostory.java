//package com.konden.projectpart2.test.table;
//
//import android.app.Application;
//
//import androidx.lifecycle.LiveData;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Repostory {
//
//
//    ProfileDao profileDao;
//    PuzzleDataDao puzzleDataDao;
//    StageDataDao stageDataDao;
//
//    public Repostory(Application application) {
//        DataBacee dataBacee = DataBacee.getDatabase(application);
//        profileDao = dataBacee.profileDao();
//        puzzleDataDao = dataBacee.puzzleDataDao();
//        stageDataDao = dataBacee.stageDataDao();
//    }
//
//    public void insertProfile(Profile... profile) {
//        DataBacee.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                profileDao.insertProfile(profile);
//            }
//        });
//    }
//
//    public void updateProfile(Profile profile) {
//        DataBacee.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                profileDao.updateProfile(profile);
//            }
//        });
//    }
//    public void  deleteProfile(){
//        DataBacee.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                profileDao.deleteProfile();
//            }
//        });
//    }
//
//
//
//    public LiveData<List<Profile>> getAllData() {
//        return profileDao.getAllData();
//    }
//
//    //------------------------------------StageDataDao---------------------------*/
//    public void insertPuzzleData(ArrayList<PuzzleData> puzzleData) {
//        DataBacee.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                puzzleDataDao.insertPuzzleData(puzzleData);
//            }
//        });
//
//
//    }
//
//    LiveData<List<PuzzleData>> getAllPuzzle(long idLevel) {
//        return puzzleDataDao.getAllPuzzle(idLevel);
//    }
//
//    public void deletePuzzle(PuzzleData... puzzleData) {
//        DataBacee.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                puzzleDataDao.deletePuzzle(puzzleData);
//            }
//        });
//
//
//    }
//
//
//    public void updatePuzzle (StagesData puzzleData){
//        DataBacee.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                puzzleDataDao.updatePuzzle(puzzleData);
//            }
//        });
//    }
//
//
//
//    public void insertStageData (ArrayList<StagesData> stagesData){
//        DataBacee.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                stageDataDao.insertStageData(stagesData);
//            }
//        });
//    }
//
//    LiveData<List<StagesData>> getAllStageData (){
//        return stageDataDao.getAllStageData();
//    }
//    LiveData<StagesData> getPoints (){
//        return stageDataDao.getPoints();
//    }
//
//
//
//}
