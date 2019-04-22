package com.example.assignmentapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Query;
import androidx.room.Room;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class TopicListActivity extends AppCompatActivity {

    public static final String DATABASE_INITIALISED = "databaseInitialised";

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private TopicAdapter mAdapter;
    private TopicAdapter mAdapter2;
    private TopicAdapter mAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);
        setTitle("Tutorials");

        Button btnTest = findViewById(R.id.buttonTester);
        recyclerView1 = findViewById(R.id.recycler1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2 = findViewById(R.id.recycler2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3 = findViewById(R.id.recycler3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Tutorial> myDataset1 = new ArrayList<>();
        myDataset1.add(new Tutorial("Introduction to Harvard Referencing", "This tutorial will introduce you to Harvard Referencing", "Referencing", "XEOCbFJjRw0", "Harvard referencing is used at UNSW."));

        mAdapter = new TopicAdapter(myDataset1);
        mAdapter2 = new TopicAdapter(new ArrayList<Tutorial>());

        SharedPreferences checkDbPrefs =  getSharedPreferences(DATABASE_INITIALISED, MODE_PRIVATE);
        //since we don't assign the value (1) for DATABASE_INITIALISED until insertDbTask we check for inequality to 1 rather than equality to 0
        if (checkDbPrefs.getInt(DATABASE_INITIALISED, 0)!=1) {
            new InsertDBTask().execute();
        }
        //ensures that we only execute the QueryDBTask if the database has been populated
        if (checkDbPrefs.getInt(DATABASE_INITIALISED,0)==1){
            new QueryDBTask().execute();
        }

        //mAdapter3 = new TopicAdapter(new ArrayList<Tutorial>());
        recyclerView1.setAdapter(mAdapter);
        recyclerView2.setAdapter(mAdapter2);
        //recyclerView3.setAdapter(mAdapter3);
    }

    public void goToTutorial(View view){
        Intent intent = new Intent(TopicListActivity.this, TutorialVidActivity.class);
        startActivity(intent);
    }

    private class QueryDBTask extends AsyncTask<Void, Void, ArrayList<Tutorial>> {

        @Override
        protected ArrayList<Tutorial> doInBackground(Void... voids){
            TutorialDatabase db = Room
                    .databaseBuilder(TopicListActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();
                return (ArrayList<Tutorial>) db.tutorialDao().getTutorials();
        }

        @Override
        protected void onPostExecute(ArrayList<Tutorial> tutorials){
            mAdapter2.setTutorials(tutorials);
            mAdapter2.notifyDataSetChanged();
        }
    }


    //write questions here. the task only executes the inserts if the database does not contain any tutorials.
    private class InsertDBTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void...voids){
            TutorialDatabase db = Room
                    .databaseBuilder(TopicListActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();

                db.tutorialDao().insert(new Tutorial("Introduction to Researching", "This tutorial will introduce you to Researching", "Researching", "XEOCbFJjRw0", "Researching is important for assignments."));
                return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            SharedPreferences prefs = getSharedPreferences(DATABASE_INITIALISED, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(DATABASE_INITIALISED, 1);
            editor.apply();
        }
    }
}
