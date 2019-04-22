
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Priorrecordids {

    @SerializedName("recordIdentifier")
    @Expose
    private RecordIdentifier recordIdentifier;

    public RecordIdentifier getRecordIdentifier() {
        return recordIdentifier;
    }

    public void setRecordIdentifier(RecordIdentifier recordIdentifier) {
        this.recordIdentifier = recordIdentifier;
    }

}
