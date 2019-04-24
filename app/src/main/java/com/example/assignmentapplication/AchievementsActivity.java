package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.assignmentapplication.QuizStartActivity.SHARED_PREFS;

//class also includes methods to globally get and add points and other metrics
public class AchievementsActivity extends AppCompatActivity {

    public static final String KEY_POINTS = "totalPoints";
    public static final String TIMES_SHARED = "timesShared";
    public static final String NOTES_WRITTEN = "notesWritten";

    private TextView textPoints;
    private TextView textShared;
    private TextView textNotes;
    private ImageView badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        setTitle("Progress");

        textPoints = findViewById(R.id.textPoints);
        textShared = findViewById(R.id.textShared);
        textNotes = findViewById(R.id.textNotes);
        badge = findViewById(R.id.badge);

        getProgress(this);

    }

    public void getProgress(Context context){
        int i = getPoints(context);
        textPoints.setText(i);
        textShared.setText("You have shared progress "+ getShares(context) + " times.");
        textNotes.setText("You have written " + getNotes(context) + " sets of notes!");
        if(i>=200){
            badge.setVisibility(View.VISIBLE);
        }

    }

    public static int getPoints(Context context){
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int points = sharedPrefs.getInt(KEY_POINTS, 0);
        return points;
    }

    public static void addPoints(Context context, int addPoints){
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int points = sharedPrefs.getInt(KEY_POINTS, 0);
        int newPoints = points + addPoints;
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(KEY_POINTS, newPoints);
        editor.commit();
    }

    public void sharePoints(View view){
        String shareMsg = "I have earned a total of " + getPoints(AchievementsActivity.this) + " points completing tutorials and quizzes!";
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "UNSW1001 App is fun!");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareMsg);
        startActivity(Intent.createChooser(sharingIntent, "Share Via"));
        addShare(AchievementsActivity.this);
        getProgress(AchievementsActivity.this);
    }

    public static void addShare(Context context){
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        int timesShared = sharedPrefs.getInt(TIMES_SHARED, 0);
        int newShared = timesShared + 1;
        editor.putInt(TIMES_SHARED, newShared);
        editor.commit();

        if(newShared%5==0){
            addPoints(context, 10);
            Toast.makeText(context, "You have earned 10 points from sharing 5 times!", Toast.LENGTH_SHORT).show();
        }
    }
    public static int getShares(Context context){
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int timesShared = sharedPrefs.getInt(TIMES_SHARED, 0);
        return timesShared;
    }

    public static void addNotes(Context context){
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        int notesWritten = sharedPrefs.getInt(NOTES_WRITTEN, 0);
        int newNotes = notesWritten + 1;
        editor.putInt(TIMES_SHARED, newNotes);
        editor.commit();

        if(newNotes%5==0){
            addPoints(context, 10);
            Toast.makeText(context, "You have earned 10 points from making 5 sets of notes!", Toast.LENGTH_SHORT).show();
        }
    }

    public static int getNotes(Context context){
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        int notesWritten = sharedPrefs.getInt(NOTES_WRITTEN, 0);
        return notesWritten;

    }
}
