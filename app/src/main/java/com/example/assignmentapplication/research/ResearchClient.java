package com.example.assignmentapplication.research;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ResearchClient {
    @GET("items.json?title={query}")
    Call<AcademicResponse> getResponse(@Path("query")String query);

}
