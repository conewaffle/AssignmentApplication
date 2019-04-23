package com.example.assignmentapplication;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    List<Note> getNotes();

    @Insert
    void insert(Note note);

    @Update
    void upDateNote(Note note);

    @Delete
    void deleteNote(Note note);
}
