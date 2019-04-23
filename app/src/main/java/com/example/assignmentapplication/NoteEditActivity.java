package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.assignmentapplication.NoteAdapter.NOTE_PARCEL;

public class NoteEditActivity extends AppCompatActivity {

    private EditText noteEdit;
    private Button noteSave;
    private EditText subjectEdit;
    private ProgressDialog progDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_create);

        noteSave = findViewById(R.id.btnSaveNote);
        noteEdit = findViewById(R.id.noteEdit);
        subjectEdit = findViewById(R.id.subjectEdit);

        progDialog = new ProgressDialog(NoteEditActivity.this);

        Intent i = getIntent();
        final Note mynote = i.getParcelableExtra(NOTE_PARCEL);
        noteEdit.setText(mynote.getBody());
        subjectEdit.setText(mynote.getSubject());

        noteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noteEdit.getText().toString().isEmpty() || subjectEdit.getText().toString().isEmpty()) {
                    Toast.makeText(NoteEditActivity.this, "Your Note or Subject cannot be empty!", Toast.LENGTH_LONG).show();
                } else {
                    String noteBody = noteEdit.getText().toString();
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String dateString = sdf.format(calendar.getTime());
                    String noteSubject = subjectEdit.getText().toString();
                    Note newNote = new Note(mynote.getId(), noteSubject, dateString, noteBody);
                    UpdateNoteTask updateNoteTask = new UpdateNoteTask();
                    updateNoteTask.execute(newNote);
                }
            }
        });

    }
    private class UpdateNoteTask extends AsyncTask<Note, Void, Void> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progDialog.setMessage("Saving Note...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.setCancelable(true);
            progDialog.show();
        }

        @Override
        protected Void doInBackground(Note... note){
            TutorialDatabase db = Room
                    .databaseBuilder(NoteEditActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();

            db.noteDao().upDateNote(note[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progDialog.dismiss();
            Toast.makeText(NoteEditActivity.this, "Note Saved", Toast.LENGTH_LONG).show();
            finish();
        }
    }

}
