package com.example.popularmovies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.GridView;

import com.example.popularmovies.R;
import com.example.popularmovies.adapter.MovieAdapter;
import com.example.popularmovies.databinding.ActivityMainBinding;
import com.example.popularmovies.model.MovieInfo;
import com.example.popularmovies.model.Result;
import com.example.popularmovies.service.MovieService;
import com.example.popularmovies.service.RetrofitInstance;
import com.example.popularmovies.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PagedList<Result> resultArrayList;
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);
        swipeRefreshLayout = activityMainBinding.swiperefresh;
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovies();
            }
        });

        getMovies();


    }

    private void getMovies() {
//        mainActivityViewModel.getAllMovieData().observe(this, new Observer<List<Result>>() {
//            @Override
//            public void onChanged(List<Result> results) {
//                resultArrayList = (ArrayList<Result>) results;
//                fillArrayList();
//            }
//        });

        mainActivityViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Result>>() {
            @Override
            public void onChanged(PagedList<Result> results) {
                resultArrayList = results;
                fillArrayList();
            }
        });
    }

    private void fillArrayList() {
        recyclerView = activityMainBinding.recyclerView;
        movieAdapter = new MovieAdapter(this);
        movieAdapter.submitList(resultArrayList);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);

    }
}
