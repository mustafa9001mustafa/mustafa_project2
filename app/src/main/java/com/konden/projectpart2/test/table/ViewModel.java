//package com.konden.projectpart2.test.table;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ViewModel extends AndroidViewModel {
//    Repostory repostory ;
//    public ViewModel(@NonNull Application application) {
//        super(application);
//        repostory = new Repostory(application);
//    }
//    public void insertProfile(Profile... profile){
//        repostory.insertProfile(profile);
//    }
//
//    public void  updateProfile(Profile profile){
//        repostory.updateProfile(profile);
//    }
//
//    public void  deleteProfile(){
//       repostory.deleteProfile();
//    }
//
//
//    public LiveData<List<Profile>> getAllData (){
//        return repostory.getAllData();
//    }
//
//    //------------------------------------StageDataDao---------------------------*/
//    public void insertPuzzleData (ArrayList<PuzzleData> puzzleData){
//       repostory.insertPuzzleData(puzzleData);
//        }
//
//
//
//    public LiveData<List<PuzzleData>> getAllPuzzle (long idLevel){
//       return repostory.getAllPuzzle(idLevel);
//    }
//
//    public void updatePuzzle (StagesData puzzleData){
//        repostory.updatePuzzle(puzzleData);
//    }
//
//
//    public void deletePuzzle (PuzzleData... puzzleData){
//      repostory.deletePuzzle(puzzleData);
//
//    }
//
//
//    public void insertStageData (ArrayList<StagesData> stagesData){
//        repostory.insertStageData(stagesData);
//    }
//
//    public LiveData<List<StagesData>> getAllStageData (){
//        return repostory.getAllStageData();
//    }
//
//    public LiveData<StagesData> getPoints (){
//        return  repostory.getPoints();
//    }
//
//}
