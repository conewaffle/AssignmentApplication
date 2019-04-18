package com.example.assignmentapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizStartActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    private TextView textHighScore;
    private int highscore;
    private TextView quizTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start);

        textHighScore = findViewById(R.id.textHighScore);
        loadHighScore();

        Intent intent = getIntent();
        quizTitle = findViewById(R.id.quizTitle);
        quizTitle.setText(intent.getStringExtra("CATEGORY") + " Quiz");
        setTitle(intent.getStringExtra("CATEGORY") + " Quiz");


    }

    public void startQuiz(View view){
        Intent intentA = getIntent();
        String category = intentA.getStringExtra("CATEGORY");
        Intent intent = new Intent(QuizStartActivity.this, QuizActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ){
            if(resultCode==RESULT_OK){
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score>highscore){
                    updateHighScore(score);
                }
            }
        }
    }

    private void loadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textHighScore.setText("Highscore: " + highscore);
    }

    private void updateHighScore(int highscoreNew){
        highscore = highscoreNew;
        textHighScore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}
