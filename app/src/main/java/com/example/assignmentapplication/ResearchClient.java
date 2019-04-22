package com.example.assignmentapplication;

import com.example.assignmentapplication.research.AcademicResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ResearchClient {
    @GET("{search}?page=1&pageSize=1&metadata=true&fulltext=false&citations=false&similar=false&duplicate=false&urls=false&faithfulMetadata=false&apiKey=OzjG4lC2IkNPtB8xiydaL1gZ9AonTbq7")
    Call<AcademicResponse> getResponse(@Path("search") String search);
}
