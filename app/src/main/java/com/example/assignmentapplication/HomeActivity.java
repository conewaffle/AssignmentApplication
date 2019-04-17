package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    public void goToQuiz(View view){
        Intent intent = new Intent(HomeActivity.this, QuizStartActivity.class);
        //TextView textView = findViewById(R.id.quizTitle);
        //String category = (String) textView.getText();
        //intent.putExtra("CATEGORY", category);
        startActivity(intent);

    }
}
