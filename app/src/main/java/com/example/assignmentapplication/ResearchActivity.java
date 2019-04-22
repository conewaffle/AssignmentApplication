package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.assignmentapplication.research.AcademicResponse;
import com.example.assignmentapplication.research.Mod;
import com.example.assignmentapplication.research.ResearchClient;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ResearchActivity extends AppCompatActivity {

    private EditText searchText;
    private Button searchButton;
    private RecyclerView recyclerView;
    private ResearchAdapter mAdapter;

    private ProgressDialog progDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research);
        setTitle("Search for Academic Resources");

        progDialog = new ProgressDialog(ResearchActivity.this);

        searchText = findViewById(R.id.searchText);
        searchButton = findViewById(R.id.btnSearch);
        recyclerView = findViewById(R.id.searchRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchText.getText().toString();

                GetResourcesTask getResourcesTask = new GetResourcesTask();
                getResourcesTask.execute(query);

            }
        });



    }

    private class GetResourcesTask extends AsyncTask<String, Void, ArrayList<Mod>>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progDialog.setMessage("Loading Topics...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progDialog.setCancelable(true);
            progDialog.show();
        }

        @Override
        protected ArrayList<Mod> doInBackground(String... query) {

            try{
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.lib.harvard.edu/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ResearchClient client = retrofit.create(ResearchClient.class);

                Call<AcademicResponse> academicCall = client.getResponse(query[0]);
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
            mAdapter = new ResearchAdapter(result);
            recyclerView.setAdapter(mAdapter);
            progDialog.dismiss();
        }
    }

}