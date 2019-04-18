package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        textScore = findViewById(R.id.textScore);
        buttonEndQuiz = findViewById(R.id.btnEndQuiz);
        gradeMessage = findViewById(R.id.textGradeMsg);

        Intent i = getIntent();
        score = i.getIntExtra(EXTRA_SCORE,0);
        totalQuestions = i.getIntExtra(TOTAL_QUESTIONS,0);

        textScore.setText(score + "/" + totalQuestions);

        grade = (score/totalQuestions)*100;

        if(grade<50){
          gradeMessage.setText("Further study is required.");
          textScore.setTextColor(getResources().getColor(R.color.colorIncorrect));
        } else if(grade<80){
            gradeMessage.setText("You can do better!");
        } else {
            gradeMessage.setText("Well Done!");
            textScore.setTextColor(getResources().getColor(R.color.colorCorrect));
        }

    }

    public void endReview(View view){
        Intent resultIntent = new Intent(QuizResultActivity.this, QuizStartActivity.class);
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }




}
