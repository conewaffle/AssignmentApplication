package com.example.assignmentapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note implements Parcelable {

    @PrimaryKey(autoGenerate = true) @NonNull
    private int id;
    private String subject;
    private String date;
    private String body;
    //Date is a String since ROOM was not able to handle Date type

    public Note(int id, String subject, String date, String body){
        this.subject = subject;
        this.date = date;
        this.body = body;
        this.id=id;
    }

    protected Note(Parcel in) {
        id = in.readInt();
        subject = in.readString();
        date = in.readString();
        body = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(subject);
        dest.writeString(date);
        dest.writeString(body);
    }
}
