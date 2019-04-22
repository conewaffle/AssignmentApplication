
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Librarycloud {

    @SerializedName("HarvardRepositories")
    @Expose
    private HarvardRepositories harvardRepositories;
    @SerializedName("originalDocument")
    @Expose
    private String originalDocument;
    @SerializedName("priorrecordids")
    @Expose
    private Priorrecordids priorrecordids;
    @SerializedName("processingDate")
    @Expose
    private String processingDate;

    public HarvardRepositories getHarvardRepositories() {
        return harvardRepositories;
    }

    public void setHarvardRepositories(HarvardRepositories harvardRepositories) {
        this.harvardRepositories = harvardRepositories;
    }

    public String getOriginalDocument() {
        return originalDocument;
    }

    public void setOriginalDocument(String originalDocument) {
        this.originalDocument = originalDocument;
    }

    public Priorrecordids getPriorrecordids() {
        return priorrecordids;
    }

    public void setPriorrecordids(Priorrecordids priorrecordids) {
        this.priorrecordids = priorrecordids;
    }

    public String getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(String processingDate) {
        this.processingDate = processingDate;
    }

}
