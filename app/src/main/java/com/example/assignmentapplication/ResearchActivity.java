package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.assignmentapplication.research.AcademicResponse;
import com.example.assignmentapplication.research.Mod;
import com.example.assignmentapplication.research.ResearchClient;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ResearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
        setTitle("Search for Academic Resources");

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.searchRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetResourcesTask getResourcesTask = new GetResourcesTask();
        getResourcesTask.execute();

    }

    private class GetResourcesTask extends AsyncTask<Void, Void, ArrayList<Mod>>{

        @Override
        protected ArrayList<Mod> doInBackground(Void... voids) {

            try{
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.lib.harvard.edu/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ResearchClient client = retrofit.create(ResearchClient.class);

                Call<AcademicResponse> academicCall = client.getResponse("peanuts");
                Response<AcademicResponse> academicResponse = academicCall.execute();
                ArrayList<Mod> myList = (ArrayList<Mod>) academicResponse.body().getItems().getMods();

                return myList;

            } catch(IOException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Mod> result){
            //mAdapter = new ResearchAdapter(result);
            //recyclerView.setAdapter(mAdapter);
        }
    }

}
