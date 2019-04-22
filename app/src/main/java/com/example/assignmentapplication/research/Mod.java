
package com.example.assignmentapplication.research;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mod {

    @SerializedName("titleInfo")
    @Expose
    private TitleInfo titleInfo;
    @SerializedName("name")
    @Expose
    private List<Name> name = null;
    @SerializedName("typeOfResource")
    @Expose
    private String typeOfResource;
    @SerializedName("genre")
    @Expose
    private List<Genre> genre = null;
    @SerializedName("originInfo")
    @Expose
    private OriginInfo originInfo;
    @SerializedName("language")
    @Expose
    private Language language;
    @SerializedName("physicalDescription")
    @Expose
    private PhysicalDescription physicalDescription;
    @SerializedName("note")
    @Expose
    private Note note;
    @SerializedName("subject")
    @Expose
    private List<Subject> subject = null;
    @SerializedName("relatedItem")
    @Expose
    private List<RelatedItem> relatedItem = null;
    @SerializedName("identifier")
    @Expose
    private List<Identifier_> identifier = null;
    @SerializedName("location")
    @Expose
    private List<Location_> location = null;
    @SerializedName("extension")
    @Expose
    private Extension extension;
    @SerializedName("recordInfo")
    @Expose
    private RecordInfo recordInfo;
    @SerializedName("classification")
    @Expose
    private List<Classification> classification = null;
    @SerializedName("tableOfContents")
    @Expose
    private String tableOfContents;
    @SerializedName("abstract")
    @Expose
    private Abstract _abstract;

    public TitleInfo getTitleInfo() {
        return titleInfo;
    }

    public void setTitleInfo(TitleInfo titleInfo) {
        this.titleInfo = titleInfo;
    }

    public List<Name> getName() {
        return name;
    }

    public void setName(List<Name> name) {
        this.name = name;
    }

    public String getTypeOfResource() {
        return typeOfResource;
    }

    public void setTypeOfResource(String typeOfResource) {
        this.typeOfResource = typeOfResource;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public OriginInfo getOriginInfo() {
        return originInfo;
    }

    public void setOriginInfo(OriginInfo originInfo) {
        this.originInfo = originInfo;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public PhysicalDescription getPhysicalDescription() {
        return physicalDescription;
    }

    public void setPhysicalDescription(PhysicalDescription physicalDescription) {
        this.physicalDescription = physicalDescription;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public List<RelatedItem> getRelatedItem() {
        return relatedItem;
    }

    public void setRelatedItem(List<RelatedItem> relatedItem) {
        this.relatedItem = relatedItem;
    }

    public List<Identifier_> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(List<Identifier_> identifier) {
        this.identifier = identifier;
    }

    public List<Location_> getLocation() {
        return location;
    }

    public void setLocation(List<Location_> location) {
        this.location = location;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

    public RecordInfo getRecordInfo() {
        return recordInfo;
    }

    public void setRecordInfo(RecordInfo recordInfo) {
        this.recordInfo = recordInfo;
    }

    public List<Classification> getClassification() {
        return classification;
    }

    public void setClassification(List<Classification> classification) {
        this.classification = classification;
    }

    public String getTableOfContents() {
        return tableOfContents;
    }

    public void setTableOfContents(String tableOfContents) {
        this.tableOfContents = tableOfContents;
    }

    public Abstract getAbstract() {
        return _abstract;
    }

    public void setAbstract(Abstract _abstract) {
        this._abstract = _abstract;
    }

}
