package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

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

        iconQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                goToQuizList(v);
            }
        });
        iconTopics.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                goToTopicList(v);
            }
        });
        iconSettings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToSettings(v);
            }
        });


    }

    public void goToQuizList(View view){
        Intent intent = new Intent(HomeActivity.this, MasterQuizActivity.class);
        startActivity(intent);
    }

    public void goToTopicList(View view){
        Intent intent = new Intent(HomeActivity.this, TopicListActivity.class);
        startActivity(intent);
    }

    public void goToSettings(View view){
        Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
}
