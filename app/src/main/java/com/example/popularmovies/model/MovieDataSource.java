package com.example.popularmovies.model;

import android.app.Application;

import com.example.popularmovies.R;
import com.example.popularmovies.service.MovieService;
import com.example.popularmovies.service.RetrofitInstance;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Result> {

    private MovieService movieService;
    private Application application;

    public MovieDataSource(MovieService movieService, Application application) {
        this.movieService = movieService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Result> callback) {

        movieService = RetrofitInstance.getService();
        Call<MovieInfo> call = movieService.getResultWithPaging(application.getApplicationContext().getString(R.string.api_key), 1);
        call.enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(Call<MovieInfo> call, Response<MovieInfo> response) {
                MovieInfo movieInfo = response.body();
                ArrayList<Result> results = new ArrayList<>();
                if (movieInfo != null && movieInfo.getResults() != null) {
                    results = (ArrayList<Result>) movieInfo.getResults();
                    callback.onResult(results, null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<MovieInfo> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull final LoadCallback<Long, Result> callback) {
        movieService = RetrofitInstance.getService();
        Call<MovieInfo> call = movieService.getResultWithPaging(application.getApplicationContext().getString(R.string.api_key), params.key);
        call.enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(Call<MovieInfo> call, Response<MovieInfo> response) {
                MovieInfo movieInfo = response.body();
                ArrayList<Result> results = new ArrayList<>();
                if (movieInfo != null && movieInfo.getResults() != null) {
                    results = (ArrayList<Result>) movieInfo.getResults();
                    callback.onResult(results, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<MovieInfo> call, Throwable t) {

            }
        });
    }
}
