
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecordInfo {

    @SerializedName("descriptionStandard")
    @Expose
    private String descriptionStandard;
    @SerializedName("recordContentSource")
    @Expose
    private RecordContentSource recordContentSource;
    @SerializedName("recordCreationDate")
    @Expose
    private RecordCreationDate recordCreationDate;
    @SerializedName("recordIdentifier")
    @Expose
    private RecordIdentifier_ recordIdentifier;
    @SerializedName("recordOrigin")
    @Expose
    private String recordOrigin;
    @SerializedName("recordChangeDate")
    @Expose
    private RecordChangeDate recordChangeDate;
    @SerializedName("languageOfCataloging")
    @Expose
    private LanguageOfCataloging languageOfCataloging;

    public String getDescriptionStandard() {
        return descriptionStandard;
    }

    public void setDescriptionStandard(String descriptionStandard) {
        this.descriptionStandard = descriptionStandard;
    }

    public RecordContentSource getRecordContentSource() {
        return recordContentSource;
    }

    public void setRecordContentSource(RecordContentSource recordContentSource) {
        this.recordContentSource = recordContentSource;
    }

    public RecordCreationDate getRecordCreationDate() {
        return recordCreationDate;
    }

    public void setRecordCreationDate(RecordCreationDate recordCreationDate) {
        this.recordCreationDate = recordCreationDate;
    }

    public RecordIdentifier_ getRecordIdentifier() {
        return recordIdentifier;
    }

    public void setRecordIdentifier(RecordIdentifier_ recordIdentifier) {
        this.recordIdentifier = recordIdentifier;
    }

    public String getRecordOrigin() {
        return recordOrigin;
    }

    public void setRecordOrigin(String recordOrigin) {
        this.recordOrigin = recordOrigin;
    }

    public RecordChangeDate getRecordChangeDate() {
        return recordChangeDate;
    }

    public void setRecordChangeDate(RecordChangeDate recordChangeDate) {
        this.recordChangeDate = recordChangeDate;
    }

    public LanguageOfCataloging getLanguageOfCataloging() {
        return languageOfCataloging;
    }

    public void setLanguageOfCataloging(LanguageOfCataloging languageOfCataloging) {
        this.languageOfCataloging = languageOfCataloging;
    }

}
