package com.example.assignmentapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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
            checkBox.setOnClickListener(this);
        }

        //clicking on the topic starts the tutorial, or sets tutorial as (un)completed
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Tutorial mTutorial = mDataset.get(position);

            switch (v.getId()){
                case R.id.topicLayout:
                    Intent tutorialIntent = new Intent(v.getContext(), TutorialVidActivity.class);
                    tutorialIntent.putExtra("TUTORIAL", mTutorial);
                    v.getContext().startActivity(tutorialIntent);
                    break;
                case R.id.checkTut:
                    if (checkBox.isChecked()){
                        mTutorial.setCompleted(1);
                        ((TopicListActivity) v.getContext()).updateTutorials(mTutorial);
                    } else {
                        mTutorial.setCompleted(0);
                        ((TopicListActivity) v.getContext()).updateTutorials(mTutorial);
                    }
                default:
            }

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
        int i = mDataset.get(position).getCompleted();
        if (i==0){
            holder.checkBox.setChecked(false);
        } else {
            holder.checkBox.setChecked(true);
        }

    }

    @Override
    public int getItemCount(){return mDataset.size();}

    public void setTutorials(ArrayList<Tutorial> tutorials){
        mDataset.clear();
        mDataset.addAll(tutorials);
    }

}
