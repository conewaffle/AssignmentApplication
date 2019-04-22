
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extension {

    @SerializedName("librarycloud")
    @Expose
    private Librarycloud librarycloud;

    public Librarycloud getLibrarycloud() {
        return librarycloud;
    }

    public void setLibrarycloud(Librarycloud librarycloud) {
        this.librarycloud = librarycloud;
    }

}
