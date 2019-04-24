package com.example.assignmentapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter mAdapter;
    private FloatingActionButton createNote;
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

        createNote = findViewById(R.id.btnCreateNote);
        createNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesListActivity.this, NoteCreateActivity.class);
                int requestCode = 1;
                startActivityForResult(intent, 1);
                //we start activity for intent so that returning here from create/edit note activities calls onActivityForResult so the recyclerView is updated with new data
            }
        });

        GetNoteTask getNoteTask = new GetNoteTask();
        getNoteTask.execute();
    }

    //every time we come back to this activity from the create/edit activities we want the recyclerView to update with any new data.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

}
