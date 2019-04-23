package com.example.assignmentapplication;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    List<Note> getNotes();

    @Insert
    void insert(Note note);

}
