package com.example.assignmentapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class TopicListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);

        setTitle("Tutorials");

        Button btnTest = findViewById(R.id.buttonTester);

    }

    public void goToTutorial(View view){
        Intent intent = new Intent(TopicListActivity.this, TutorialVidActivity.class);
        startActivity(intent);
    }
}
