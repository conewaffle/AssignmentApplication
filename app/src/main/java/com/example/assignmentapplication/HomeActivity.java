package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TOTAL_POINTS = "totalPoints";

    private ImageView iconQuiz;
    private ImageView iconTopics;
    private ImageView iconSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iconQuiz = findViewById(R.id.iconQuiz);
        iconTopics = findViewById(R.id.iconTopics);
        iconSettings = findViewById(R.id.iconSettings);

        iconQuiz.setOnClickListener(this);
        iconTopics.setOnClickListener(this);
        iconSettings.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.iconQuiz:
                intent = new Intent(HomeActivity.this, MasterQuizActivity.class);
                break;
            case R.id.iconTopics:
                intent = new Intent(HomeActivity.this, TopicListActivity.class);
                break;
            case R.id.iconSettings:
                intent = new Intent(HomeActivity.this, SettingsActivity.class);
                break;
            default:
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

}
