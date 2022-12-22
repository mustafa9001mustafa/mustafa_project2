
package com.konden.projectpart2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("answer_1")
    @Expose
    public String answer1;
    @SerializedName("answer_2")
    @Expose
    public String answer2;
    @SerializedName("answer_3")
    @Expose
    public String answer3;
    @SerializedName("answer_4")
    @Expose
    public String answer4;
    @SerializedName("true_answer")
    @Expose
    public String trueAnswer;
    @SerializedName("points")
    @Expose
    public Integer points;
    @SerializedName("duration")
    @Expose
    public Integer duration;
    @SerializedName("pattern")
    @Expose
    public Pattern pattern;
    @SerializedName("hint")
    @Expose
    public String hint;

}
