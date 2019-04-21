package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TOTAL_POINTS = "totalPoints";

    private CardView cardTopics;
    private CardView cardQuiz;
    private CardView cardSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardTopics = findViewById(R.id.cardTopics);
        cardQuiz = findViewById(R.id.cardQuiz);
        cardSettings = findViewById(R.id.cardSettings);

        cardTopics.setOnClickListener(this);
        cardQuiz.setOnClickListener(this);
        cardSettings.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.cardQuiz:
                intent = new Intent(HomeActivity.this, MasterQuizActivity.class);
                break;
            case R.id.cardTopics:
                intent = new Intent(HomeActivity.this, TopicListActivity.class);
                break;
            case R.id.cardSettings:
                intent = new Intent(HomeActivity.this, SettingsActivity.class);
                break;
            default:
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

}
