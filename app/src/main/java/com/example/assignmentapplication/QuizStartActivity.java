package com.example.assignmentapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.assignmentapplication.MasterQuizActivity.CATEGORY;

public class QuizStartActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";
    public static final String KEY_HIGHSCORERESEARCH = "keyHighScoreResearching";
    public static final String KEY_HIGHSCOREREF = "keyHighScoreReferencing";
    public static final String KEY_HIGHSCOREWRITE = "keyHighScoreWriting";

    private String category;

    private TextView textHighScore;
    private int highscore;
    private TextView quizTitle;
    private FloatingActionButton quizInstruct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start);

        Intent intentCategory = getIntent();
        category = intentCategory.getStringExtra(CATEGORY);

        quizTitle = findViewById(R.id.quizTitle);
        quizTitle.setText(category + " Quiz");
        setTitle(category + " Quiz");

        textHighScore = findViewById(R.id.textHighScore);
        loadHighScore();

        quizInstruct = findViewById(R.id.quizHelp);
        quizInstruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(QuizStartActivity.this)
                        .setTitle("Quiz Instructions")
                        .setMessage("The quiz contains a shuffled list of questions, each with 4 multiple choice options. You have 20 seconds to select an answer and press the next button. If you do not select an answer within this time-frame, you will automatically proceed to the next question.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }}).show();
            }
        });


    }

    public void startQuiz(View view){
        Intent intent = new Intent(QuizStartActivity.this, QuizActivity.class);
        intent.putExtra(CATEGORY, category);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    //updates highscore and points
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ){
            if(resultCode==RESULT_OK){
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score>highscore){
                    updateHighScore(score);
                }

                int pointsAdded = score*2;
                Toast.makeText(QuizStartActivity.this, "You have earned " + pointsAdded + " points from the quiz!", Toast.LENGTH_LONG).show();
                AchievementsActivity.addPoints(QuizStartActivity.this, pointsAdded);
            }
        }
    }

    //loads high score for the current category/topic
    private void loadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE+category, 0);
        textHighScore.setText("Highscore: " + highscore);
    }

    //updates the high score for the current category/topic
    private void updateHighScore(int highscoreNew){
        highscore = highscoreNew;
        textHighScore.setText("Highscore: " + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE+category, highscore);
        editor.apply();
    }
}
