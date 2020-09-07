package com.example.popularmovies.service;

import com.example.popularmovies.model.MovieInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

        @GET("3/movie/popular/")
    Call<MovieInfo> getResult(@Query("api_key") String apiKey);

    @GET("3/movie/popular/")
    Call<MovieInfo> getResultWithPaging(@Query("api_key") String apiKey, @Query("page") long page);
}
