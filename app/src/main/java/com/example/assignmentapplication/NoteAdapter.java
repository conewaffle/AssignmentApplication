package com.example.assignmentapplication;

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
            v.getContext().startActivity(viewIntent);
        }
    }

    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_notes_list_item, parent, false);
        NoteAdapter.NoteViewHolder vh = new NoteAdapter.NoteViewHolder(myView);
        return vh;
    }

    @Override
    public void onBindViewHolder(NoteAdapter.NoteViewHolder holder, int position){
        holder.subject.setText(mDataset.get(position).getSubject());
        holder.date.setText(mDataset.get(position).getDate());
    }

    @Override
    public int getItemCount(){return mDataset.size();}


}
