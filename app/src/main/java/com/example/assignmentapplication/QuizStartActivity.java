package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start);

        Button startQuiz = findViewById(R.id.quizStart);

    }

    public void startQuiz(View view){
        Intent intent = new Intent(QuizStartActivity.this, QuizActivity.class);
        startActivity(intent);
    }

}
