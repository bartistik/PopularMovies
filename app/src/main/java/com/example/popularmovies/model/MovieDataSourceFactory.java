package com.example.popularmovies.model;

import android.app.Application;

import com.example.popularmovies.service.MovieService;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieDataSource movieDataSource;
    private Application application;
    private MovieService movieService;
    private MutableLiveData<MovieDataSource> mutableLiveData;

    public MovieDataSourceFactory(Application application, MovieService movieService) {
        this.application = application;
        this.movieService = movieService;
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {

        movieDataSource = new MovieDataSource(movieService, application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
