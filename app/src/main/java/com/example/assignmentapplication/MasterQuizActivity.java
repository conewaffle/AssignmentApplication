package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MasterQuizActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_quiz);
        setTitle("All Quizzes");

    }

    public void goToQuiz(View view){
        Intent intent = new Intent(MasterQuizActivity.this, QuizStartActivity.class);
        Button button = (Button) view;
        String category = (String) button.getText();
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}
