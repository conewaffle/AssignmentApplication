package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.assignmentapplication.QuizStartActivity.SHARED_PREFS;

//class also includes methods to globally get and add points and other metrics
public class AchievementsActivity extends AppCompatActivity {

    public static final String KEY_POINTS = "totalPoints";
    public static final String TIMES_SHARED = "timesShared";
    public static final String NOTES_WRITTEN = "notesWritten";
    public static final String SOURCES_DOWNLOADED = "sourcesDownloaded";
    public static final String TUTES_COMPLETED = "tutesCompleted";

    private TextView textPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        setTitle("Progress");

        textPoints = findViewById(R.id.textPoints);
        textPoints.setText(Integer.toString(getPoints(this)));

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
    }
}
