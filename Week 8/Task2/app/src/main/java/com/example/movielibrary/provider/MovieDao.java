package com.example.movielibrary.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface MovieDao {

    @Query("select * from movies")
    LiveData<List<Movie>> getAllMovie();

    @Query("select * from movies where movieId=:movie")
    List<Movie> getMovie(String movie);

    @Insert
    void addMovie(Movie car);

    @Query("delete from movies where yearId=:year")
    void deleteYear(int year);

    @Query("delete from movies")
    void deleteAllMovies();
}
