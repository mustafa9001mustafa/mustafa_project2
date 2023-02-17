//package com.konden.projectpart2.test.table;
//
//import androidx.room.Entity;
//import androidx.room.ForeignKey;
//import androidx.room.PrimaryKey;
//
//@Entity(foreignKeys = {@ForeignKey(entity = StagesData.class, parentColumns = {"id"},childColumns = {"numberLavel"},onUpdate = ForeignKey.CASCADE,onDelete = ForeignKey.CASCADE)})
////بيانات الالغاز
//public class PuzzleData {
//    @PrimaryKey(autoGenerate = true)
//    private int id ;
//    //set qu id here
//    private int puzzleId;
//    private String textGas ;
//    private String answer_1;
//    private String answer_2;
//    private String answer_3;
//    private String answer_4;
//    private String theCorrecteAnswer;
//    private int numberPoints;
//    private int numberLavel;
//    private int timeSounds;
//    private String InstutionalText;
//    private int patterNumber;
//    private String paaterName;
//
//    public PuzzleData() {
//    }
//
//    public PuzzleData(int puzzleId, String textGas, String answer_1, String answer_2, String answer_3, String answer_4, String theCorrecteAnswer, int numberPoints, int numberLavel, int timeSounds, String instutionalText, int patterNumber, String paaterName) {
//        this.puzzleId = puzzleId;
//        this.textGas = textGas;
//        this.answer_1 = answer_1;
//        this.answer_2 = answer_2;
//        this.answer_3 = answer_3;
//        this.answer_4 = answer_4;
//        this.theCorrecteAnswer = theCorrecteAnswer;
//        this.numberPoints = numberPoints;
//        this.numberLavel = numberLavel;
//        this.timeSounds = timeSounds;
//        InstutionalText = instutionalText;
//        this.patterNumber = patterNumber;
//        this.paaterName = paaterName;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getPuzzleId() {
//        return puzzleId;
//    }
//
//    public void setPuzzleId(int puzzleId) {
//        this.puzzleId = puzzleId;
//    }
//
//    public String getTextGas() {
//        return textGas;
//    }
//
//    public void setTextGas(String textGas) {
//        this.textGas = textGas;
//    }
//
//    public String getAnswer_1() {
//        return answer_1;
//    }
//
//    public void setAnswer_1(String answer_1) {
//        this.answer_1 = answer_1;
//    }
//
//    public String getAnswer_2() {
//        return answer_2;
//    }
//
//    public void setAnswer_2(String answer_2) {
//        this.answer_2 = answer_2;
//    }
//
//    public String getAnswer_3() {
//        return answer_3;
//    }
//
//    public void setAnswer_3(String answer_3) {
//        this.answer_3 = answer_3;
//    }
//
//    public String getAnswer_4() {
//        return answer_4;
//    }
//
//    public void setAnswer_4(String answer_4) {
//        this.answer_4 = answer_4;
//    }
//
//    public String getTheCorrecteAnswer() {
//        return theCorrecteAnswer;
//    }
//
//    public void setTheCorrecteAnswer(String theCorrecteAnswer) {
//        this.theCorrecteAnswer = theCorrecteAnswer;
//    }
//
//    public int getNumberPoints() {
//        return numberPoints;
//    }
//
//    public void setNumberPoints(int numberPoints) {
//        this.numberPoints = numberPoints;
//    }
//
//    public int getNumberLavel() {
//        return numberLavel;
//    }
//
//    public void setNumberLavel(int numberLavel) {
//        this.numberLavel = numberLavel;
//    }
//
//    public int getTimeSounds() {
//        return timeSounds;
//    }
//
//    public void setTimeSounds(int timeSounds) {
//        this.timeSounds = timeSounds;
//    }
//
//    public String getInstutionalText() {
//        return InstutionalText;
//    }
//
//    public void setInstutionalText(String instutionalText) {
//        InstutionalText = instutionalText;
//    }
//
//    public int getPatterNumber() {
//        return patterNumber;
//    }
//
//    public void setPatterNumber(int patterNumber) {
//        this.patterNumber = patterNumber;
//    }
//
//    public String getPaaterName() {
//        return paaterName;
//    }
//
//    public void setPaaterName(String paaterName) {
//        this.paaterName = paaterName;
//    }
//}
