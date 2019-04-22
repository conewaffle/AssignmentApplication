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
import android.widget.Toast;

import com.example.assignmentapplication.research.AcademicResponse;
import com.example.assignmentapplication.research.Datum;

import java.io.IOException;
import java.util.ArrayList;

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
        setTitle("Search for Academic Articles");

        progDialog = new ProgressDialog(ResearchActivity.this);

        searchText = findViewById(R.id.searchText);
        searchButton = findViewById(R.id.btnSearch);
        recyclerView = findViewById(R.id.searchRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //pressing search button takes EditText text and uses it as a query for the API. It checks to ensure the query exists (otherwise API Call would fail)
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchText.getText().toString();

                if(query.isEmpty()){
                    Toast.makeText(ResearchActivity.this, "Error! There is nothing to search for!", Toast.LENGTH_LONG).show();
                } else {
                    GetResourcesTask getResourcesTask = new GetResourcesTask();
                    getResourcesTask.execute(query);
                }
            }
        });



    }

    private class GetResourcesTask extends AsyncTask<String, Void, ArrayList<Datum>>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progDialog.setMessage("Loading Academic Articles...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.setCancelable(true);
            progDialog.show();
        }

        @Override
        protected ArrayList<Datum> doInBackground(String... query) {

            try{
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://core.ac.uk/api-v2/articles/search/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ResearchClient client = retrofit.create(ResearchClient.class);

                Call<AcademicResponse> academicCall = client.getResponse(query[0]);
                Response<AcademicResponse> academicResponse = academicCall.execute();
                ArrayList<Datum> myList = (ArrayList<Datum>) academicResponse.body().getData();

                return myList;

            } catch(IOException e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Datum> result){
            mAdapter = new ResearchAdapter(result);
            recyclerView.setAdapter(mAdapter);
            progDialog.dismiss();
        }
    }

}
