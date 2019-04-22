
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HarvardRepositories {

    @SerializedName("HarvardRepository")
    @Expose
    private String harvardRepository;

    public String getHarvardRepository() {
        return harvardRepository;
    }

    public void setHarvardRepository(String harvardRepository) {
        this.harvardRepository = harvardRepository;
    }

}
