
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("numFound")
    @Expose
    private Integer numFound;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("start")
    @Expose
    private Integer start;

    public Integer getNumFound() {
        return numFound;
    }

    public void setNumFound(Integer numFound) {
        this.numFound = numFound;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

}
