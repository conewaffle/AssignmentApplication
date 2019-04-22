
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Role {

    @SerializedName("roleTerm")
    @Expose
    private RoleTerm roleTerm;

    public RoleTerm getRoleTerm() {
        return roleTerm;
    }

    public void setRoleTerm(RoleTerm roleTerm) {
        this.roleTerm = roleTerm;
    }

}
