
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subject {

    @SerializedName("@authority")
    @Expose
    private String authority;
    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("geographicCode")
    @Expose
    private GeographicCode geographicCode;
    @SerializedName("geographic")
    @Expose
    private String geographic;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("hierarchicalGeographic")
    @Expose
    private HierarchicalGeographic hierarchicalGeographic;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public GeographicCode getGeographicCode() {
        return geographicCode;
    }

    public void setGeographicCode(GeographicCode geographicCode) {
        this.geographicCode = geographicCode;
    }

    public String getGeographic() {
        return geographic;
    }

    public void setGeographic(String geographic) {
        this.geographic = geographic;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public HierarchicalGeographic getHierarchicalGeographic() {
        return hierarchicalGeographic;
    }

    public void setHierarchicalGeographic(HierarchicalGeographic hierarchicalGeographic) {
        this.hierarchicalGeographic = hierarchicalGeographic;
    }

}
