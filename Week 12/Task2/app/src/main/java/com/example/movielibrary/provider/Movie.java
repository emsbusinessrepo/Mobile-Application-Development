package com.example.movielibrary.provider;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {
    public static final String tableName = "movies";
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "itemId")
    private int id;

    @ColumnInfo(name = "movieId")
    private String movie;

    @ColumnInfo(name = "yearId")
    private int year;

    @ColumnInfo(name = "countryId")
    private String country;

    @ColumnInfo(name = "genreId")
    private String genre;

    @ColumnInfo(name = "costId")
    private int cost;

    @ColumnInfo(name = "keywordsId")
    private String keywords;

    public Movie(String movie, int year, String country, String genre, int cost, String keywords){
        this.movie = movie;
        this.year = year;
        this.country = country;
        this.genre = genre;
        this.cost = cost;
        this.keywords = keywords;
    }

    public int getId() {return id;}
    public void setId(@NonNull int id){
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public String getGenre() {
        return genre;
    }

    public int getCost() {
        return cost;
    }

    public String getKeywords() {
        return keywords;
    }

}
