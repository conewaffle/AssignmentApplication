package com.example.assignmentapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.assignmentapplication.QuizStartActivity.KEY_HIGHSCORE;
import static com.example.assignmentapplication.QuizStartActivity.KEY_HIGHSCOREREF;
import static com.example.assignmentapplication.QuizStartActivity.KEY_HIGHSCORERESEARCH;
import static com.example.assignmentapplication.QuizStartActivity.KEY_HIGHSCOREWRITE;
import static com.example.assignmentapplication.QuizStartActivity.SHARED_PREFS;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnResetScores;
    private Button btnResetPoints;
    private Button btnResetCounts;
    private Button btnDeleteNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        btnResetScores = findViewById(R.id.btnResetScores);
        btnResetPoints = findViewById(R.id.btnResetPoints);
        btnResetCounts = findViewById(R.id.btnResetCounts);
        btnDeleteNotes = findViewById(R.id.btnDeleteNotes);
        btnResetScores.setOnClickListener(this);
        btnResetPoints.setOnClickListener(this);
        btnResetCounts.setOnClickListener(this);
        btnDeleteNotes.setOnClickListener(this);

    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id.btnResetScores:
                new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle("Confirm Reset")
                        .setMessage("Are you sure you want to reset high scores for all quizzes?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                resetScores(v);
                                Toast.makeText(SettingsActivity.this, "High Scores for all quizzes have now been reset. Your points are unaffected.", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                break;
            case R.id.btnResetPoints:
                new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle("Confirm Reset")
                        .setMessage("Are you sure you want to reset your points? You may lose a badge.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                int currentPoints = -1*(AchievementsActivity.getPoints(SettingsActivity.this));
                                AchievementsActivity.addPoints(SettingsActivity.this, currentPoints);
                                Toast.makeText(SettingsActivity.this, "Points have been reset.", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                break;
            case R.id.btnResetCounts:
                new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle("Confirm Reset")
                        .setMessage("Are you sure you want to reset progress counts for Shares and Notes? You may lose a badge.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                AchievementsActivity.resetCounts(SettingsActivity.this);
                                Toast.makeText(SettingsActivity.this, "Progress counts have been reset.", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                break;
            case R.id.btnDeleteNotes:
                new AlertDialog.Builder(SettingsActivity.this)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to delete all your notes?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DeleteNoteTask deleteTask = new DeleteNoteTask();
                                deleteTask.execute();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            default:
        }


    }

    //method for resetting quiz high scores. change for all categories if adding new categories
    private void resetScores(View view){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, 0);
        editor.putInt(KEY_HIGHSCOREREF, 0);
        editor.putInt(KEY_HIGHSCORERESEARCH,0);
        editor.putInt(KEY_HIGHSCOREWRITE, 0);
        editor.apply();
    }

    private class DeleteNoteTask extends AsyncTask<Note, Void, Void> {

        ProgressDialog progDialog = new ProgressDialog(SettingsActivity.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progDialog.setMessage("Deleting all notes...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.setCancelable(true);
            progDialog.show();
        }

        @Override
        protected Void doInBackground(Note... note){
            TutorialDatabase db = Room
                    .databaseBuilder(SettingsActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();

            db.noteDao().deleteAllNotes();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progDialog.dismiss();
            Toast.makeText(SettingsActivity.this, "Notes Deleted", Toast.LENGTH_SHORT).show();
        }
    }

}
