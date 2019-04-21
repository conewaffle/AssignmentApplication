package com.example.assignmentapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tutorial")
public class Tutorial {

    @PrimaryKey
    private String title;
    private String shortDesc;
    private String category;
    private String vidLink;
    private String tutorialBody;

    public Tutorial(){

    }

    public Tutorial(String title, String shortDesc, String category, String vidLink, String tutorialBody) {
        this.title = title;
        this.shortDesc = shortDesc;
        this.category = category;
        this.vidLink = vidLink;
        this.tutorialBody = tutorialBody;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVidLink() {
        return vidLink;
    }

    public void setVidLink(String vidLink) {
        this.vidLink = vidLink;
    }

    public String getTutorialBody() {
        return tutorialBody;
    }

    public void setTutorialBody(String tutorialBody) {
        this.tutorialBody = tutorialBody;
    }
}
