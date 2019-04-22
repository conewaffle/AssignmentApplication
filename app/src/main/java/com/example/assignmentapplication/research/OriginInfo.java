
package com.example.assignmentapplication.research;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OriginInfo {

    @SerializedName("place")
    @Expose
    private List<Place> place = null;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("dateIssued")
    @Expose
    private List<String> dateIssued = null;
    @SerializedName("issuance")
    @Expose
    private String issuance;
    @SerializedName("edition")
    @Expose
    private List<String> edition = null;

    public List<Place> getPlace() {
        return place;
    }

    public void setPlace(List<Place> place) {
        this.place = place;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<String> getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(List<String> dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getIssuance() {
        return issuance;
    }

    public void setIssuance(String issuance) {
        this.issuance = issuance;
    }

    public List<String> getEdition() {
        return edition;
    }

    public void setEdition(List<String> edition) {
        this.edition = edition;
    }

}
