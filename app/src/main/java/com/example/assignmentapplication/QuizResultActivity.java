package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.assignmentapplication.MasterQuizActivity.CATEGORY;
import static com.example.assignmentapplication.QuizActivity.EXTRA_SCORE;
import static com.example.assignmentapplication.QuizActivity.TOTAL_QUESTIONS;

public class QuizResultActivity extends AppCompatActivity {

    private TextView textScore;
    private TextView gradeMessage;
    private Button buttonShare;
    private Button buttonEndQuiz;
    private double grade;
    private int score;
    private int totalQuestions;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        setTitle("Quiz Results");

        textScore = findViewById(R.id.textScore);
        buttonEndQuiz = findViewById(R.id.btnEndQuiz);
        gradeMessage = findViewById(R.id.textGradeMsg);

        Intent i = getIntent();
        score = i.getIntExtra(EXTRA_SCORE,0);
        totalQuestions = i.getIntExtra(TOTAL_QUESTIONS,0);
        category = i.getStringExtra(CATEGORY);

        textScore.setText(score + "/" + totalQuestions);

        //this method adds a message and changes colour of score depending on how good it was.
        setGrade(score, totalQuestions);

        //updates the points
        int pointsAdded = score*2;
        Toast.makeText(QuizResultActivity.this, "You have earned " + pointsAdded + " points!", Toast.LENGTH_LONG).show();
        AchievementsActivity.addPoints(QuizResultActivity.this, pointsAdded);
    }

    public void endReview(View view){
        Intent resultIntent = new Intent(QuizResultActivity.this, QuizStartActivity.class);
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void shareResult(View view){
        String shareMsg = "I Scored " + score + "/" + totalQuestions + " in the " + category + " quiz!";
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "I just finished a quiz!");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareMsg);
        startActivity(Intent.createChooser(sharingIntent, "Share Via"));
    }

    public void setGrade(int score, int totalQuestions){
        grade = ((double)score/(double)totalQuestions)*100;

        if(grade<50){
            gradeMessage.setText("Further study is required.");
            textScore.setTextColor(getResources().getColor(R.color.colorIncorrect));
        } else if(grade<75){
            gradeMessage.setText("You can do better!");
            textScore.setTextColor(Color.BLACK);
        } else {
            gradeMessage.setText("Well Done!");
            textScore.setTextColor(getResources().getColor(R.color.colorCorrect));
        }
    }


}
