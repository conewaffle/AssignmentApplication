package com.example.assignmentapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={Tutorial.class}, version=1, exportSchema = false)
public abstract class TutorialDatabase extends RoomDatabase {
    public abstract TutorialDao tutorialDao();
}
