package com.example.assignmentapplication;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    public static final String NOTE_PARCEL = "noteParcel";

    private ArrayList<Note> mDataset;

    public NoteAdapter(ArrayList<Note> myDataset) { mDataset= myDataset;}

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView date;
        public TextView subject;
        public CardView noteCard;

        public NoteViewHolder(View itemView){
            super(itemView);
            date = itemView.findViewById(R.id.textNoteDate);
            subject = itemView.findViewById(R.id.textNoteSubject);
            noteCard = itemView.findViewById(R.id.noteCard);

            noteCard.setOnClickListener(this);

        }

        @Override
        public void onClick(View v){
            int position = getAdapterPosition();
            Note myNote = mDataset.get(position);
            Intent viewIntent = new Intent(v.getContext(), NoteViewActivity.class);
            viewIntent.putExtra(NOTE_PARCEL, myNote);
            int resultCode = 1;
            ((Activity) v.getContext()).startActivityForResult(viewIntent, 1);
        }
    }

    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_notes_list_item, parent, false);
        NoteAdapter.NoteViewHolder vh = new NoteAdapter.NoteViewHolder(myView);
        return vh;
    }

    //if the subject is No Subject, the adapter sets text to the note's body instead.
    @Override
    public void onBindViewHolder(NoteAdapter.NoteViewHolder holder, int position){
        holder.date.setText(mDataset.get(position).getDate());
        if(mDataset.get(position).getSubject().equals("No Subject")){
            holder.subject.setText(mDataset.get(position).getBody());
        } else
        holder.subject.setText(mDataset.get(position).getSubject());
    }

    @Override
    public int getItemCount(){return mDataset.size();}


}
