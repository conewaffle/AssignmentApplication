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

import static com.example.assignmentapplication.TutorialVidActivity.TUTE_TOPIC;

public class NoteCreateActivity extends AppCompatActivity {

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
        setTitle("New Note");

        progDialog = new ProgressDialog(NoteCreateActivity.this);

        Intent intent = getIntent();

        //receives the intent if from the Tutorial Activity to populate the subject and produce a message.
        if (intent.getStringExtra(TUTE_TOPIC)==null){
        } else {
            subjectEdit.setText(intent.getStringExtra(TUTE_TOPIC));
            Toast.makeText(NoteCreateActivity.this, "Create notes on what you have just learnt!", Toast.LENGTH_LONG).show();
        }

        noteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noteEdit.getText().toString().isEmpty() || subjectEdit.getText().toString().isEmpty()) {
                    Toast.makeText(NoteCreateActivity.this, "Your Note or Subject cannot be empty!", Toast.LENGTH_LONG).show();
                } else {
                    String noteBody = noteEdit.getText().toString();
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String dateString = sdf.format(calendar.getTime());
                    String noteSubject = subjectEdit.getText().toString();
                    Note myNote = new Note(0, noteSubject, dateString, noteBody);
                    InsertNoteTask insertNoteTask = new InsertNoteTask();
                    insertNoteTask.execute(myNote);
                }
            }
        });

    }

    //inserts note into database
    private class InsertNoteTask extends AsyncTask<Note, Void, Void> {

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
                    .databaseBuilder(NoteCreateActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();

            db.noteDao().insert(note[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            progDialog.dismiss();
            Toast.makeText(NoteCreateActivity.this, "Note Saved", Toast.LENGTH_LONG).show();
            finish();
        }
    }


}
