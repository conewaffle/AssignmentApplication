
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LanguageTerm {

    @SerializedName("@authority")
    @Expose
    private String authority;
    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("#text")
    @Expose
    private String text;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
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
