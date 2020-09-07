package com.example.popularmovies.viewmodel;

import android.app.Application;

import com.example.popularmovies.model.MovieDataSource;
import com.example.popularmovies.model.MovieDataSourceFactory;
import com.example.popularmovies.model.MovieRepository;
import com.example.popularmovies.model.Result;
import com.example.popularmovies.service.MovieService;
import com.example.popularmovies.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Result>> pagedListLiveData;

    private LiveData<Result> resultLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        MovieService movieService = RetrofitInstance.getService();
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory(application, movieService);
        movieDataSourceLiveData = movieDataSourceFactory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(3)
                .build();

        executor = Executors.newCachedThreadPool();
        pagedListLiveData = new LivePagedListBuilder<Long, Result>(movieDataSourceFactory, config).setFetchExecutor(executor).build();
    }

    public LiveData<List<Result>> getAllMovieData() {
        return movieRepository.getMutableLiveData();
    }

    public LiveData<PagedList<Result>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
