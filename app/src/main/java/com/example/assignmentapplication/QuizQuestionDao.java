package com.example.assignmentapplication;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface QuizQuestionDao {
    @Query("SELECT * FROM quizquestion")
    List<QuizQuestion> getQuestions();

    @Query("SELECT * FROM quizquestion WHERE category = :category")
    List<QuizQuestion> getCategoryQuestions(String category);

    @Insert
    void insert(QuizQuestion quizQuestion);

}
