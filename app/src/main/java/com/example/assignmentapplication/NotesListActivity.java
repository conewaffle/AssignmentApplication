package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter mAdapter;
    private ProgressDialog progDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        setTitle("Your Notes");

        progDialog = new ProgressDialog(NotesListActivity.this);

        recyclerView = findViewById(R.id.noteRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetNoteTask getNoteTask = new GetNoteTask();
        getNoteTask.execute();

    }


    private class GetNoteTask extends AsyncTask<Void, Void, ArrayList<Note>> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progDialog.setMessage("Loading Notes...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.setCancelable(true);
            progDialog.show();
        }

        @Override
        protected ArrayList<Note> doInBackground(Void... voids){
            TutorialDatabase db = Room
                    .databaseBuilder(NotesListActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();

            ArrayList<Note> noteList = (ArrayList<Note>) db.noteDao().getNotes();

            return noteList;
        }

        @Override
        protected void onPostExecute(ArrayList<Note> result){
            mAdapter = new NoteAdapter(result);
            recyclerView.setAdapter(mAdapter);
            progDialog.dismiss();
        }
    }


    //put this in an activity that writes notes.
    private class InsertNoteTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void...voids){
            TutorialDatabase db = Room
                    .databaseBuilder(NotesListActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new GetNoteTask().execute();
        }
    }
}
