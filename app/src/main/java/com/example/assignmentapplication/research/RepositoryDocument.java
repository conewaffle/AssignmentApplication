
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepositoryDocument {

    @SerializedName("pdfStatus")
    @Expose
    private Integer pdfStatus;
    @SerializedName("metadataAdded")
    @Expose
    private Long metadataAdded;
    @SerializedName("metadataUpdated")
    @Expose
    private Long metadataUpdated;
    @SerializedName("depositedDate")
    @Expose
    private Long depositedDate;

    public Integer getPdfStatus() {
        return pdfStatus;
    }

    public void setPdfStatus(Integer pdfStatus) {
        this.pdfStatus = pdfStatus;
    }

    public Long getMetadataAdded() {
        return metadataAdded;
    }

    public void setMetadataAdded(Long metadataAdded) {
        this.metadataAdded = metadataAdded;
    }

    public Long getMetadataUpdated() {
        return metadataUpdated;
    }

    public void setMetadataUpdated(Long metadataUpdated) {
        this.metadataUpdated = metadataUpdated;
    }

    public Long getDepositedDate() {
        return depositedDate;
    }

    public void setDepositedDate(Long depositedDate) {
        this.depositedDate = depositedDate;
    }

}
