package com.example.assignmentapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tutorial")
public class Tutorial implements Parcelable {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    private int id;
    private String title;
    private String shortDesc;
    private String category;
    private String vidLink;
    private String tutorialBody;
    private int completed;

    @Ignore
    public Tutorial(){

    }

    public Tutorial(int id, String title, String shortDesc, String category, String vidLink, String tutorialBody, int completed) {
        this.id = id;
        this.title = title;
        this.shortDesc = shortDesc;
        this.category = category;
        this.vidLink = vidLink;
        this.tutorialBody = tutorialBody;
        this.completed = completed;
    }

    protected Tutorial(Parcel in) {
        id = in.readInt();
        title = in.readString();
        shortDesc = in.readString();
        category = in.readString();
        vidLink = in.readString();
        tutorialBody = in.readString();
        completed = in.readInt();
    }

    public static final Creator<Tutorial> CREATOR = new Creator<Tutorial>() {
        @Override
        public Tutorial createFromParcel(Parcel in) {
            return new Tutorial(in);
        }

        @Override
        public Tutorial[] newArray(int size) {
            return new Tutorial[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(shortDesc);
        dest.writeString(category);
        dest.writeString(vidLink);
        dest.writeString(tutorialBody);
        dest.writeInt(completed);
    }


}
