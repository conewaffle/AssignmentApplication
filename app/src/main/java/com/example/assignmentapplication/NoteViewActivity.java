package com.example.assignmentapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.assignmentapplication.NoteAdapter.NOTE_PARCEL;

public class NoteViewActivity extends AppCompatActivity {

    private ProgressDialog progDialog;
    private TextView subject;
    private TextView body;
    private TextView date;
    private FloatingActionButton edit;
    private FloatingActionButton delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);


        subject = findViewById(R.id.viewnoteSubject);
        body = findViewById(R.id.viewNoteBody);
        date = findViewById(R.id.viewNoteDate);
        edit = findViewById(R.id.btnEditNote);
        delete = findViewById(R.id.btnDeleteNote);

        progDialog = new ProgressDialog(NoteViewActivity.this);

        Intent i = getIntent();
        final Note myNote = i.getParcelableExtra(NOTE_PARCEL);
        subject.setText(myNote.getSubject());
        date.setText(myNote.getDate());
        body.setText(myNote.getBody());
        setTitle(myNote.getSubject());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(NoteViewActivity.this, NoteEditActivity.class);
                editIntent.putExtra(NOTE_PARCEL, myNote);
                editIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(editIntent);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new AlertDialog.Builder(NoteViewActivity.this)
                        .setTitle("Confirm Delete")
                        .setMessage("Are you sure you want to delete this note?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DeleteNoteTask deleteNoteTask = new DeleteNoteTask();
                                deleteNoteTask.execute(myNote);
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }

    private class DeleteNoteTask extends AsyncTask<Note, Void, Void> {

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
                    .databaseBuilder(NoteViewActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();

            db.noteDao().deleteNote(note[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progDialog.dismiss();
            Toast.makeText(NoteViewActivity.this, "Note Deleted", Toast.LENGTH_LONG).show();
            finish();
        }
    }

}
