package com.example.assignmentapplication;

import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder> {

    private ArrayList<Tutorial> mDataset;

    public TopicAdapter(ArrayList<Tutorial> myDataset){mDataset=myDataset;}

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textTute;
        public CheckBox checkBox;
        public LinearLayout topicLayout;

        public MyViewHolder(View itemView){
            super(itemView);
            textTute = itemView.findViewById(R.id.textTutorial);
            checkBox = itemView.findViewById(R.id.checkTut);
            topicLayout = itemView.findViewById(R.id.topicLayout);

            topicLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Tutorial mTutorial = mDataset.get(position);
            Intent tutorialIntent = new Intent(v.getContext(), TutorialVidActivity.class);
            tutorialIntent.putExtra("TUTORIAL", mTutorial);
            v.getContext().startActivity(tutorialIntent);

        }
    }


    @Override
    public TopicAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_topic_list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(myView);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        holder.textTute.setText(mDataset.get(position).getTitle());

    }

    @Override
    public int getItemCount(){return mDataset.size();}

    public void setTutorials(ArrayList<Tutorial> tutorials){
        mDataset.clear();
        mDataset.addAll(tutorials);
    }
}
