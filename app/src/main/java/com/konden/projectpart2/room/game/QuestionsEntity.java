package com.konden.projectpart2.room.game;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuestionsEntity {
    @PrimaryKey(autoGenerate = true)
    private int id_Question;
    private String title;
    private String answer_1;
    private String answer_2;
    private String answer_3;
    private String answer_4;
    private String true_answer;
    private int points;
    private int duration;
    private String hint;

    public int getId_Question() {
        return id_Question;
    }

    public void setId_Question(int id_Question) {
        this.id_Question = id_Question;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer_1() {
        return answer_1;
    }

    public void setAnswer_1(String answer_1) {
        this.answer_1 = answer_1;
    }

    public String getAnswer_2() {
        return answer_2;
    }

    public void setAnswer_2(String answer_2) {
        this.answer_2 = answer_2;
    }

    public String getAnswer_3() {
        return answer_3;
    }

    public void setAnswer_3(String answer_3) {
        this.answer_3 = answer_3;
    }

    public String getAnswer_4() {
        return answer_4;
    }

    public void setAnswer_4(String answer_4) {
        this.answer_4 = answer_4;
    }

    public String getTrue_answer() {
        return true_answer;
    }

    public void setTrue_answer(String true_answer) {
        this.true_answer = true_answer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public QuestionsEntity(String title, String answer_1, String answer_2, String answer_3, String answer_4, String true_answer, int points, int duration, String hint) {
        this.title = title;
        this.answer_1 = answer_1;
        this.answer_2 = answer_2;
        this.answer_3 = answer_3;
        this.answer_4 = answer_4;
        this.true_answer = true_answer;
        this.points = points;
        this.duration = duration;
        this.hint = hint;


    }
}
