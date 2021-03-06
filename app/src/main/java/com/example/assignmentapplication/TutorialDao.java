package com.example.assignmentapplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TutorialDao {
    @Query("SELECT * FROM tutorial")
    List<Tutorial> getTutorials();

    @Query("SELECT * FROM tutorial WHERE category = 'Researching'")
    List<Tutorial> getResearchingTutorials();

    @Query("SELECT * FROM tutorial WHERE category = 'Referencing'")
    List<Tutorial> getReferencingTutorials();

    @Query("SELECT * FROM tutorial WHERE category = 'Writing'")
    List<Tutorial> getWritingTutorials();

    @Insert
    void insert(Tutorial tutorial);

    @Update
    void updateTutorial(Tutorial tutorial);
}
