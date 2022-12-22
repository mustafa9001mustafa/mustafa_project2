
package com.konden.projectpart2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pattern {

    @SerializedName("pattern_id")
    @Expose
    public Integer patternId;
    @SerializedName("pattern_name")
    @Expose
    public String patternName;

}
