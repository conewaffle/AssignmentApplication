
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location_ {

    @SerializedName("physicalLocation")
    @Expose
    private PhysicalLocation physicalLocation;
    @SerializedName("shelfLocator")
    @Expose
    private String shelfLocator;

    public PhysicalLocation getPhysicalLocation() {
        return physicalLocation;
    }

    public void setPhysicalLocation(PhysicalLocation physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    public String getShelfLocator() {
        return shelfLocator;
    }

    public void setShelfLocator(String shelfLocator) {
        this.shelfLocator = shelfLocator;
    }

}
