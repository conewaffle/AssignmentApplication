package com.example.assignmentapplication;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmentapplication.research.Datum;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ResearchAdapter extends RecyclerView.Adapter<ResearchAdapter.ResearchViewHolder>{

    private ArrayList<Datum> mDataset;

    public ResearchAdapter(ArrayList<Datum> myDataset){mDataset = myDataset;}

    public class ResearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView titleYear, author, pub, itemType;
        public CardView researchCard;

        public ResearchViewHolder(View itemView){
            super(itemView);
            titleYear = itemView.findViewById(R.id.researchTitle);
            author = itemView.findViewById(R.id.researchAuthor);
            pub = itemView.findViewById(R.id.researchPub);
            itemType = itemView.findViewById(R.id.researchType);
            researchCard = itemView.findViewById(R.id.cardResearch);

            researchCard.setOnClickListener(this);
        }

        //clicking on the research item will download it as a PDF through a browser link
        @Override
        public void onClick(View view){
            int position = getAdapterPosition();
            Datum myDatum = mDataset.get(position);

            String s = myDatum.getDownloadUrl();
            if(s!=null){
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                view.getContext().startActivity(browserIntent);
            } else {
                Toast.makeText(view.getContext(), "Unfortunately this resource does not have a download link", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public ResearchAdapter.ResearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View researchView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_research_item, parent, false);
        ResearchViewHolder vh = new ResearchViewHolder(researchView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ResearchViewHolder holder, int position){
        if(mDataset.get(position).getYear()==null){
            holder.titleYear.setText(mDataset.get(position).getTitle() + " (no date)");
        } else {
            holder.titleYear.setText(mDataset.get(position).getTitle() + " (" + Integer.toString(mDataset.get(position).getYear()) + ")");
        }

        if(mDataset.get(position).getAuthors().isEmpty()) {
            holder.author.setText("No Authors");
        } else if (mDataset.get(position).getAuthors().size()==1){
                holder.author.setText(mDataset.get(position).getAuthors().get(0));
        } else {holder.author.setText(mDataset.get(position).getAuthors().get(0) + " et al.");}

        if(mDataset.get(position).getPublisher()==null){
            holder.pub.setText("No Publisher");
        } else {
            holder.pub.setText(mDataset.get(position).getPublisher());
        }
        if(mDataset.get(position).getSubjects().isEmpty()) {
            holder.itemType.setText("Data not available");
        } else {
            holder.itemType.setText(mDataset.get(position).getSubjects().get(0)) ;
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
