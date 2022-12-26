package com.konden.projectpart2.room.game.pattern;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pattern {
    @PrimaryKey
    private int pattern_id;
    private String pattern_name;

    public Pattern(int pattern_id, String pattern_name) {
        this.pattern_id = pattern_id;
        this.pattern_name = pattern_name;
    }

    public int getPattern_id() {
        return pattern_id;
    }

    public void setPattern_id(int pattern_id) {
        this.pattern_id = pattern_id;
    }

    public String getPattern_name() {
        return pattern_name;
    }

    public void setPattern_name(String pattern_name) {
        this.pattern_name = pattern_name;
    }
}
