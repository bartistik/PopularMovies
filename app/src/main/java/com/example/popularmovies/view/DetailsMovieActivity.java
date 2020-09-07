package com.example.popularmovies.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.popularmovies.R;
import com.example.popularmovies.adapter.MovieAdapter;
import com.example.popularmovies.databinding.ActivityDetailsMovieBinding;
import com.example.popularmovies.databinding.ActivityMainBinding;
import com.example.popularmovies.model.Result;

public class DetailsMovieActivity extends AppCompatActivity {

    private Result result;
    private ActivityDetailsMovieBinding activityDetailsMovieBinding;

    private static final String BASE_IMAGE_PATH = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);

        activityDetailsMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_details_movie);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("movieData")) {

            result = intent.getParcelableExtra("movieData");

            activityDetailsMovieBinding.setResult(result);
            setTitle(result.getTitle());

//            titleMovieTextView.setText(result.getTitle());
//            descriptionMovieTextView.setText(result.getOverview());
//
//            Glide
//                    .with(this)
//                    .load(MovieAdapter.BASE_IMAGE_PATH + result.getPosterPath())
//                    .placeholder(R.drawable.progress_circle)
//                    .fitCenter()
//                    .into(posterMovieImageView);

        }
//
//
//
//        titleMovieTextView.setText(getIntent().getStringExtra("title"));
//        descriptionMovieTextView.setText(getIntent().getStringExtra("description"));
//
//        Glide
//                .with(this)
//                .load(getIntent().getStringExtra("image"))
//                .fitCenter()
//                .into(posterMovieImageView);

    }
}
