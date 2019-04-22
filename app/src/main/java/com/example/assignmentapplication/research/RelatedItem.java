
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedItem {

    @SerializedName("titleInfo")
    @Expose
    private TitleInfo_ titleInfo;
    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("@displayLabel")
    @Expose
    private String displayLabel;
    @SerializedName("@otherType")
    @Expose
    private String otherType;
    @SerializedName("name")
    @Expose
    private Name_ name;
    @SerializedName("originInfo")
    @Expose
    private OriginInfo_ originInfo;
    @SerializedName("identifier")
    @Expose
    private Identifier identifier;
    @SerializedName("location")
    @Expose
    private Location location;

    public TitleInfo_ getTitleInfo() {
        return titleInfo;
    }

    public void setTitleInfo(TitleInfo_ titleInfo) {
        this.titleInfo = titleInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDisplayLabel() {
        return displayLabel;
    }

    public void setDisplayLabel(String displayLabel) {
        this.displayLabel = displayLabel;
    }

    public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
    }

    public Name_ getName() {
        return name;
    }

    public void setName(Name_ name) {
        this.name = name;
    }

    public OriginInfo_ getOriginInfo() {
        return originInfo;
    }

    public void setOriginInfo(OriginInfo_ originInfo) {
        this.originInfo = originInfo;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
