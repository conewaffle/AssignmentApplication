package com.example.assignmentapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignmentapplication.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyQuiz.db";
    private static final int DATABASE_VERSION = 1;

    //holds reference to database
    private SQLiteDatabase db;

    public QuizDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_ANSWERA + " TEXT, " +
                QuestionsTable.COLUMN_ANSWERB + " TEXT, " +
                QuestionsTable.COLUMN_ANSWERC + " TEXT, " +
                QuestionsTable.COLUMN_ANSWERD + " TEXT, " +
                QuestionsTable.COLUMN_CORRECT_ANSWER + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        QuizQuestion q1 = new QuizQuestion("A is correct", "A", "B", "C", "D", 1);
        addQuizQuestion(q1);
        QuizQuestion q2 = new QuizQuestion("B is correct", "A", "B", "C", "D", 2);
        addQuizQuestion(q2);
        QuizQuestion q3 = new QuizQuestion("C is correct", "A", "B", "C", "D", 3);
        addQuizQuestion(q3);
        QuizQuestion q4 = new QuizQuestion("D is correct", "A", "B", "C", "D", 4);
        addQuizQuestion(q4);
        QuizQuestion q5 = new QuizQuestion("B is correct", "A", "B", "C", "D", 2);
        addQuizQuestion(q5);
    }

    private void addQuizQuestion(QuizQuestion question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_ANSWERA, question.getAnswerA());
        cv.put(QuestionsTable.COLUMN_ANSWERB, question.getAnswerB());
        cv.put(QuestionsTable.COLUMN_ANSWERC, question.getAnswerC());
        cv.put(QuestionsTable.COLUMN_ANSWERD, question.getAnswerD());
        cv.put(QuestionsTable.COLUMN_CORRECT_ANSWER, question.getCorrectAnswer());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }


    public List<QuizQuestion> getQuestions(){
        List<QuizQuestion> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()){
            do{
                QuizQuestion question = new QuizQuestion();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setAnswerA(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ANSWERA)));
                question.setAnswerB(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ANSWERB)));
                question.setAnswerC(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ANSWERC)));
                question.setAnswerD(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ANSWERD)));
                question.setCorrectAnswer(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CORRECT_ANSWER)));
                questionList.add(question);

            } while(c.moveToNext());

        }
        c.close();
        return questionList;

    }
}
