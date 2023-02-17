//package com.konden.projectpart2.test.table;
//
////بيانات المراحل
//
//import static androidx.room.ForeignKey.CASCADE;
//
//import androidx.room.Entity;
//import androidx.room.ForeignKey;
//import androidx.room.PrimaryKey;
//import androidx.room.TypeConverters;
//
//import com.example.gasgame.room.cnverterDate.CnverterDate;
//
//@Entity // ربط
//@TypeConverters(CnverterDate.class)
////بيانات الملراحل
//public class StagesData {
//  @PrimaryKey(autoGenerate = true)
//  private   int id ;
//  private   int numberLavel;
//  private   int numberPoints ;
//
//  public StagesData(int numberLavel, int numberPoints) {
//    this.numberLavel = numberLavel;
//    this.numberPoints = numberPoints;
//  }
//
//  public StagesData(int numberPoints) {
//    this.numberPoints = numberPoints;
//  }
//
//  public StagesData() {
//  }
//
//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }
//
//  public int getNumberLavel() {
//
//    return numberLavel;
//  }
//
//  public void setNumberLavel(int numberLavel) {
//    this.numberLavel = numberLavel;
//  }
//
//  public int getNumberPoints() {
//
//    return numberPoints;
//  }
//
//  public void setNumberPoints(int numberPoints) {
//    this.numberPoints = numberPoints;
//  }
//}
