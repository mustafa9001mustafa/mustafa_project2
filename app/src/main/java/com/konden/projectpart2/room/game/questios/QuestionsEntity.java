package com.konden.projectpart2.room.game.questios;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.konden.projectpart2.room.game.level.LevelEntity;

@Entity(foreignKeys = {
        @ForeignKey(entity = LevelEntity.class, parentColumns = {"level_no"},
                childColumns = {"levelNoChild"}, onDelete = CASCADE, onUpdate = CASCADE)})


public class QuestionsEntity {
    @PrimaryKey(autoGenerate = true)
    private int id_forDatabase;
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
    private int levelNoChild;
    private int pattern_idChild;

    public QuestionsEntity() {
    }

    public QuestionsEntity(int id_Question, String title, String answer_1, String answer_2, String answer_3, String answer_4, String true_answer, int points, int duration, String hint, int levelNoChild
            , int pattern_idChild) {

        this.id_Question = id_Question;
        this.title = title;
        this.answer_1 = answer_1;
        this.answer_2 = answer_2;
        this.answer_3 = answer_3;
        this.answer_4 = answer_4;
        this.true_answer = true_answer;
        this.points = points;
        this.duration = duration;
        this.hint = hint;
        this.levelNoChild = levelNoChild;
        this.pattern_idChild = pattern_idChild;
    }

    public int getPattern_idChild() {
        return pattern_idChild;
    }

    public void setPattern_idChild(int pattern_idChild) {
        this.pattern_idChild = pattern_idChild;
    }

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

    public int getLevelNoChild() {
        return levelNoChild;
    }

    public void setLevelNoChild(int levelNoChild) {
        this.levelNoChild = levelNoChild;
    }

    public int getId_forDatabase() {
        return id_forDatabase;
    }

    public void setId_forDatabase(int id_forDatabase) {
        this.id_forDatabase = id_forDatabase;
    }
}
