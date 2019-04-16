package com.example.assignmentapplication;

import android.provider.BaseColumns;

//this class contains constants for our SQLite operations
public final class QuizContract {

    //to ensure no one can make an instance of this clas.
    private QuizContract(){}

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_ANSWERA = "answerA";
        public static final String COLUMN_ANSWERB = "answerB";
        public static final String COLUMN_ANSWERC = "answerC";
        public static final String COLUMN_ANSWERD = "answerD";
        public static final String COLUMN_CORRECT_ANSWER = "correct_answer";

    }


}
