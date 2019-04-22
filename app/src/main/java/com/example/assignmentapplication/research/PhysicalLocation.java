
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhysicalLocation {

    @SerializedName("@valueURI")
    @Expose
    private String valueURI;
    @SerializedName("@displayLabel")
    @Expose
    private String displayLabel;
    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("#text")
    @Expose
    private String text;

    public String getValueURI() {
        return valueURI;
    }

    public void setValueURI(String valueURI) {
        this.valueURI = valueURI;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }

    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
