package com.example.assignmentapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignmentapplication.research.Mod;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ResearchAdapter extends RecyclerView.Adapter<ResearchAdapter.ResearchViewHolder>{

    private ArrayList<Mod> mDataset;

    public ResearchAdapter(ArrayList<Mod> myDataset){mDataset = myDataset;}

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

        //finish getting link.
        @Override
        public void onClick(View view){
            int position = getAdapterPosition();
            Mod myMod = mDataset.get(position);
            String s = myMod.getPhysicalDescription().getExtent();
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
        if (mDataset.get(position).getOriginInfo().getPublisher()!=null){
            holder.pub.setText(mDataset.get(position).getOriginInfo().getPublisher());
        } else holder.pub.setText("No Publisher");
        if (mDataset.get(position).getTitleInfo().getTitle()!=null) {
            holder.titleYear.setText(mDataset.get(position).getTitleInfo().getTitle());
        } else holder.titleYear.setText("No Title");
        if (mDataset.get(position).getPhysicalDescription().getExtent()!=null) {
            holder.author.setText(mDataset.get(position).getPhysicalDescription().getExtent());
        } else holder.author.setText("No Details");
        if (mDataset.get(position).getTypeOfResource()!=null) {
            holder.itemType.setText(mDataset.get(position).getTypeOfResource());
        } else holder.itemType.setText("No Details");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
