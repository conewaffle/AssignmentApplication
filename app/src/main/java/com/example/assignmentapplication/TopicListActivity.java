package com.example.assignmentapplication;

import android.app.Activity;
import android.app.ProgressDialog;
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

    private ProgressDialog progDialog;
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

        progDialog = new ProgressDialog(TopicListActivity.this);

        recyclerView1 = findViewById(R.id.recycler1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2 = findViewById(R.id.recycler2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3 = findViewById(R.id.recycler3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new TopicAdapter(new ArrayList<Tutorial>());
        mAdapter2 = new TopicAdapter(new ArrayList<Tutorial>());
        mAdapter3 = new TopicAdapter(new ArrayList<Tutorial>());

        recyclerView1.setAdapter(mAdapter);
        recyclerView2.setAdapter(mAdapter2);
        recyclerView3.setAdapter(mAdapter3);

        //this value will determine whether the app will insert tutorials into database, or proceed directly to querying them
        SharedPreferences checkDbPrefs =  getSharedPreferences(DATABASE_INITIALISED, MODE_PRIVATE);

        //since we don't assign the value (1) for DATABASE_INITIALISED until insertDbTask we check for inequality to 1 rather than equality to 0
        if (checkDbPrefs.getInt(DATABASE_INITIALISED, 0)!=1) {
            new InsertDBTask().execute();
        } else {
            new QueryDBTask().execute();
        }

    }

    //result is an ArrayList containing an ArrayList of tutorials for each category/topic of learning
    private class QueryDBTask extends AsyncTask<Void, Void, ArrayList<ArrayList<Tutorial>>> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progDialog.setMessage("Loading Topics...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.setCancelable(true);
            progDialog.show();
        }

        @Override
        protected ArrayList<ArrayList<Tutorial>> doInBackground(Void... voids){
            TutorialDatabase db = Room
                    .databaseBuilder(TopicListActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();

                //casting the List into ArrayList, since the DAO was not working with ArrayLists
                ArrayList<Tutorial> set1 = (ArrayList<Tutorial>) db.tutorialDao().getWritingTutorials();
                ArrayList<Tutorial> set2 = (ArrayList<Tutorial>) db.tutorialDao().getResearchingTutorials();
                ArrayList<Tutorial> set3 = (ArrayList<Tutorial>) db.tutorialDao().getReferencingTutorials();
                ArrayList<ArrayList<Tutorial>> masterList = new ArrayList<>();
                masterList.add(set1);
                masterList.add(set2);
                masterList.add(set3);
                return masterList;
        }

        @Override
        protected void onPostExecute(ArrayList<ArrayList<Tutorial>> tutorials){
            mAdapter.setTutorials(tutorials.get(0));
            mAdapter.notifyDataSetChanged();
            mAdapter2.setTutorials(tutorials.get(1));
            mAdapter2.notifyDataSetChanged();
            mAdapter3.setTutorials(tutorials.get(2));
            mAdapter3.notifyDataSetChanged();
            progDialog.dismiss();
        }
    }


    //write tutorials here. the task only executes the inserts if the database does not contain any tutorials.
    //onPostExecute then executes QueryDBTask to ensure serial processing of InsertDBTask and QueryDBTask
    private class InsertDBTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void...voids){
            TutorialDatabase db = Room
                    .databaseBuilder(TopicListActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();
                db.tutorialDao().insert(new Tutorial("Introduction to Researching", "This tutorial will introduce you to Researching", "Researching", "XEOCbFJjRw0", "Researching is important for assignments."));
                db.tutorialDao().insert(new Tutorial("Introduction to Harvard Referencing", "This tutorial will introduce you to Harvard Referencing", "Referencing", "XEOCbFJjRw0", "Harvard referencing is used at UNSW."));
                db.tutorialDao().insert(new Tutorial("How to Approach the Assignment Question", "This tutorial will discuss how you can approach assignment questions.", "Writing", "XE0CbFJjRw0", "Assignment writing is fun!"));
                return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            SharedPreferences prefs = getSharedPreferences(DATABASE_INITIALISED, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(DATABASE_INITIALISED, 1);
            editor.apply();
            new QueryDBTask().execute();
        }
    }
}
