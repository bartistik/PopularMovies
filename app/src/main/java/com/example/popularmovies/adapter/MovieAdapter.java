package com.example.popularmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.popularmovies.databinding.MovieItemBinding;
import com.example.popularmovies.view.DetailsMovieActivity;
import com.example.popularmovies.R;
import com.example.popularmovies.model.Result;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends PagedListAdapter<Result, MovieAdapter.MovieViewHolder> {

    private Context context;

    public MovieAdapter(Context context) {
        super(Result.CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item, parent, false);

        return new MovieViewHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Result result = getItem(position);
        holder.movieItemBinding.setResult(result);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private MovieItemBinding movieItemBinding;


        public MovieViewHolder(@NonNull MovieItemBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;

            movieItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION) {
                        Result result = getItem(position);
                        Intent intent = new Intent(itemView.getContext(), DetailsMovieActivity.class);
                        intent.putExtra("movieData", result);
                        itemView.getContext().startActivity(intent);
                    }


                }
            });

        }
    }
}
