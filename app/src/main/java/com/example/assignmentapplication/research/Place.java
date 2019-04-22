
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place {

    @SerializedName("placeTerm")
    @Expose
    private PlaceTerm placeTerm;

    public PlaceTerm getPlaceTerm() {
        return placeTerm;
    }

    public void setPlaceTerm(PlaceTerm placeTerm) {
        this.placeTerm = placeTerm;
    }

}
