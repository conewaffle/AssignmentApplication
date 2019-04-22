
package com.example.assignmentapplication.research;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("languageTerm")
    @Expose
    private List<LanguageTerm> languageTerm = null;

    public List<LanguageTerm> getLanguageTerm() {
        return languageTerm;
    }

    public void setLanguageTerm(List<LanguageTerm> languageTerm) {
        this.languageTerm = languageTerm;
    }

}
