
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repository {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("openDoarId")
    @Expose
    private Integer openDoarId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("uri")
    @Expose
    private Object uri;
    @SerializedName("urlHomepage")
    @Expose
    private Object urlHomepage;
    @SerializedName("urlOaipmh")
    @Expose
    private Object urlOaipmh;
    @SerializedName("uriJournals")
    @Expose
    private Object uriJournals;
    @SerializedName("physicalName")
    @Expose
    private String physicalName;
    @SerializedName("source")
    @Expose
    private Object source;
    @SerializedName("software")
    @Expose
    private Object software;
    @SerializedName("metadataFormat")
    @Expose
    private Object metadataFormat;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("journal")
    @Expose
    private Object journal;
    @SerializedName("roarId")
    @Expose
    private Integer roarId;
    @SerializedName("pdfStatus")
    @Expose
    private Object pdfStatus;
    @SerializedName("nrUpdates")
    @Expose
    private Integer nrUpdates;
    @SerializedName("disabled")
    @Expose
    private Boolean disabled;
    @SerializedName("lastUpdateTime")
    @Expose
    private Object lastUpdateTime;
    @SerializedName("repositoryLocation")
    @Expose
    private Object repositoryLocation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOpenDoarId() {
        return openDoarId;
    }

    public void setOpenDoarId(Integer openDoarId) {
        this.openDoarId = openDoarId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getUri() {
        return uri;
    }

    public void setUri(Object uri) {
        this.uri = uri;
    }

    public Object getUrlHomepage() {
        return urlHomepage;
    }

    public void setUrlHomepage(Object urlHomepage) {
        this.urlHomepage = urlHomepage;
    }

    public Object getUrlOaipmh() {
        return urlOaipmh;
    }

    public void setUrlOaipmh(Object urlOaipmh) {
        this.urlOaipmh = urlOaipmh;
    }

    public Object getUriJournals() {
        return uriJournals;
    }

    public void setUriJournals(Object uriJournals) {
        this.uriJournals = uriJournals;
    }

    public String getPhysicalName() {
        return physicalName;
    }

    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getSoftware() {
        return software;
    }

    public void setSoftware(Object software) {
        this.software = software;
    }

    public Object getMetadataFormat() {
        return metadataFormat;
    }

    public void setMetadataFormat(Object metadataFormat) {
        this.metadataFormat = metadataFormat;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getJournal() {
        return journal;
    }

    public void setJournal(Object journal) {
        this.journal = journal;
    }

    public Integer getRoarId() {
        return roarId;
    }

    public void setRoarId(Integer roarId) {
        this.roarId = roarId;
    }

    public Object getPdfStatus() {
        return pdfStatus;
    }

    public void setPdfStatus(Object pdfStatus) {
        this.pdfStatus = pdfStatus;
    }

    public Integer getNrUpdates() {
        return nrUpdates;
    }

    public void setNrUpdates(Integer nrUpdates) {
        this.nrUpdates = nrUpdates;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Object getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Object lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Object getRepositoryLocation() {
        return repositoryLocation;
    }

    public void setRepositoryLocation(Object repositoryLocation) {
        this.repositoryLocation = repositoryLocation;
    }

}
