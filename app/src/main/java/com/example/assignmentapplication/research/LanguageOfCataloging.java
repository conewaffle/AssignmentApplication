
package com.example.assignmentapplication.research;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LanguageOfCataloging {

    @SerializedName("languageTerm")
    @Expose
    private LanguageTerm_ languageTerm;

    public LanguageTerm_ getLanguageTerm() {
        return languageTerm;
    }

    public void setLanguageTerm(LanguageTerm_ languageTerm) {
        this.languageTerm = languageTerm;
    }

}
