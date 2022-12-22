
package com.konden.projectpart2.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("level_no")
    @Expose
    public Integer levelNo;
    @SerializedName("unlock_points")
    @Expose
    public Integer unlockPoints;
    @SerializedName("questions")
    @Expose
    public List<Question> questions = null;

}
