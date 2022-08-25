package com.example.movielibrary;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielibrary.provider.Movie;
import com.example.movielibrary.provider.MovieViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListAllMovies extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MovieRecycleAdapter adapter;
    FloatingActionButton fab;
    MovieViewModel movieViewModel;

    //ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_all);

        recyclerView = findViewById(R.id.movieRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        String st =  getIntent().getExtras().getString("key");

//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<Movie>>(){}.getType();
//        movies = gson.fromJson(st,type);

//        adapter = new MovieRecycleAdapter();
//        adapter.setData(movies);
//        recyclerView.setAdapter(adapter);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        adapter = new MovieRecycleAdapter(movieViewModel);
        recyclerView.setAdapter(adapter);

        movieViewModel.getAllMovies().observe(this, newData -> {
            adapter.setMovies(newData);
            adapter.notifyDataSetChanged();
        });

        // extra Task w6

        fab = findViewById(R.id.backButtonID);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
