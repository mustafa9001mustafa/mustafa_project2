package com.konden.projectpart2.room.game.level;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LevelEntity {
    @PrimaryKey
    private int level_no;
    private int unlock_points;
    private boolean level_status;

    public LevelEntity(int level_no, int unlock_points) {
        this.level_no = level_no;
        this.unlock_points = unlock_points;
    }

    public boolean isLevel_status() {
        return level_status;
    }

    public void setLevel_status(boolean level_status) {
        this.level_status = level_status;
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
