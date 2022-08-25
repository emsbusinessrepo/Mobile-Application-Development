package com.example.movielibrary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielibrary.provider.Movie;
import com.example.movielibrary.provider.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieRecycleAdapter extends RecyclerView.Adapter<MovieRecycleAdapter.ViewHolder> {

    //ArrayList<Movie> movies = new ArrayList<>();
    List<Movie> movies = new ArrayList<>();
    MovieViewModel movieViewModel;

    MovieRecycleAdapter(MovieViewModel viewModel) {movieViewModel = viewModel;}

    public void setMovies(List<Movie> movies){this.movies = movies;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false); //CardView inflated as RecyclerView list item
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecycleAdapter.ViewHolder viewHolder, int position) {
        viewHolder.itemTitle.setText(movies.get(position).getMovie());
        viewHolder.itemYear.setText(Integer.toString(movies.get(position).getYear()));
        viewHolder.itemCountry.setText(movies.get(position).getCountry());
        viewHolder.itemGenre.setText(movies.get(position).getGenre());
        viewHolder.itemCost.setText(Integer.toString(movies.get(position).getCost()));
        viewHolder.itemKeywords.setText(movies.get(position).getKeywords());

        // extra task

        final int fPosition = position;
        String movie = movies.get(position).getMovie();
        // newly created
        int years = movies.get(position).getYear();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // w7 extra task on click method
                movieViewModel.deleteYear(years);
                Toast.makeText(view.getContext(), "Movie no." + fPosition + " with Title:" + movie, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemTitle;
        public TextView itemYear;
        public TextView itemCountry;
        public TextView itemGenre;
        public TextView itemCost;
        public TextView itemKeywords;

        public ViewHolder(View itemView){
            super(itemView);
            itemTitle = itemView.findViewById(R.id.titlecardID);
            itemYear = itemView.findViewById(R.id.yearcardID);
            itemCountry = itemView.findViewById(R.id.countrycardID);
            itemGenre = itemView.findViewById(R.id.genrecardID);
            itemCost = itemView.findViewById(R.id.costcardID);
            itemKeywords = itemView.findViewById(R.id.keywordcardID);
        }

    }
}

