package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import static com.example.assignmentapplication.QuizStartActivity.KEY_HIGHSCORE;
import static com.example.assignmentapplication.QuizStartActivity.KEY_HIGHSCOREREF;
import static com.example.assignmentapplication.QuizStartActivity.KEY_HIGHSCORERESEARCH;
import static com.example.assignmentapplication.QuizStartActivity.SHARED_PREFS;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

    }


    //change for  all categories
    private void resetScores(View view){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, 0);
        editor.putInt(KEY_HIGHSCOREREF, 0);
        editor.putInt(KEY_HIGHSCORERESEARCH,0);
        editor.apply();
    }
}
