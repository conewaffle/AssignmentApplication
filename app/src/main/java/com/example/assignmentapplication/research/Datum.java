
package com.example.assignmentapplication.research;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("authors")
    @Expose
    private List<String> authors = null;
    @SerializedName("contributors")
    @Expose
    private List<Object> contributors = null;
    @SerializedName("datePublished")
    @Expose
    private String datePublished;
    @SerializedName("identifiers")
    @Expose
    private List<String> identifiers = null;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("relations")
    @Expose
    private List<String> relations = null;
    @SerializedName("repositories")
    @Expose
    private List<Repository> repositories = null;
    @SerializedName("repositoryDocument")
    @Expose
    private RepositoryDocument repositoryDocument;
    @SerializedName("subjects")
    @Expose
    private List<String> subjects = null;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("topics")
    @Expose
    private List<String> topics = null;
    @SerializedName("types")
    @Expose
    private List<Object> types = null;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("fulltextIdentifier")
    @Expose
    private String fulltextIdentifier;
    @SerializedName("oai")
    @Expose
    private String oai;
    @SerializedName("downloadUrl")
    @Expose
    private String downloadUrl;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("journals")
    @Expose
    private List<Journal> journals = null;
    @SerializedName("doi")
    @Expose
    private String doi;
    @SerializedName("language")
    @Expose
    private Language language;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<Object> getContributors() {
        return contributors;
    }

    public void setContributors(List<Object> contributors) {
        this.contributors = contributors;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(List<String> identifiers) {
        this.identifiers = identifiers;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<String> getRelations() {
        return relations;
    }

    public void setRelations(List<String> relations) {
        this.relations = relations;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public RepositoryDocument getRepositoryDocument() {
        return repositoryDocument;
    }

    public void setRepositoryDocument(RepositoryDocument repositoryDocument) {
        this.repositoryDocument = repositoryDocument;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<Object> getTypes() {
        return types;
    }

    public void setTypes(List<Object> types) {
        this.types = types;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getFulltextIdentifier() {
        return fulltextIdentifier;
    }

    public void setFulltextIdentifier(String fulltextIdentifier) {
        this.fulltextIdentifier = fulltextIdentifier;
    }

    public String getOai() {
        return oai;
    }

    public void setOai(String oai) {
        this.oai = oai;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Journal> getJournals() {
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

}
