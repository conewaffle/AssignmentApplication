
package com.example.assignmentapplication.research;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Journal {

    @SerializedName("title")
    @Expose
    private Object title;
    @SerializedName("identifiers")
    @Expose
    private List<String> identifiers = null;

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<String> identifiers) {
        this.identifiers = identifiers;
    }

}
