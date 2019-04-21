package com.example.assignmentapplication;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder> {

    private ArrayList<String> mDataset;

    public TopicAdapter(ArrayList<String> myDataset){mDataset=myDataset;}

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView textTute;
        public CheckBox checkBox;

        public MyViewHolder(View itemView){
            super(itemView);
            textTute = itemView.findViewById(R.id.textTutorial);
            checkBox = itemView.findViewById(R.id.checkTut);

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
        holder.textTute.setText(mDataset.get(position));

    }

    @Override
    public int getItemCount(){return mDataset.size();}

}
