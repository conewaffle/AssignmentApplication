package com.example.assignmentapplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TutorialDao {
    @Query("SELECT * FROM tutorial")
    List<Tutorial> getTutorials();

    @Query("SELECT * FROM tutorial WHERE category = 'Researching'")
    List<Tutorial> getResearchingTutorials();

    @Query("SELECT * FROM tutorial WHERE category = 'Referencing'")
    List<Tutorial> getReferencingTutorials();

    @Insert
    void insert(Tutorial tutorial);
}
