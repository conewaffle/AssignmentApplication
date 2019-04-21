package com.example.assignmentapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        Button btnReset = findViewById(R.id.btnResetScores);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle("Confirm Reset")
                        .setMessage("Are you sure you want to reset the high scores for all quizzes?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                resetScores(v);
                                Toast.makeText(SettingsActivity.this, "High Scores for all quizzes have now been reset. Your points are unaffected.", Toast.LENGTH_LONG).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

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
