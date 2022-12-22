package com.konden.projectpart2.room.game;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LevelEntity {
    @PrimaryKey(autoGenerate = true)
    private int id_level;
    private int level_no;
    private int unlock_points;

    public LevelEntity(int level_no, int unlock_points) {
        this.level_no = level_no;
        this.unlock_points = unlock_points;
    }

    public int getId_level() {
        return id_level;
    }

    public void setId_level(int id_level) {
        this.id_level = id_level;
    }

    public int getLevel_no() {
        return level_no;
    }

    public void setLevel_no(int level_no) {
        this.level_no = level_no;
    }

    public int getUnlock_points() {
        return unlock_points;
    }

    public void setUnlock_points(int unlock_points) {
        this.unlock_points = unlock_points;
    }
}
