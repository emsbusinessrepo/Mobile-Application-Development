package com.example.movielibrary;

public class Movie {
    private String movie;
    private int year;
    private String country;
    private String genre;
    private int cost;
    private String keywords;

    public Movie(String movie, int year, String country, String genre, int cost, String keywords){
        this.movie = movie;
        this.year = year;
        this.country = country;
        this.genre = genre;
        this.cost = cost;
        this.keywords = keywords;
    }

    public String getMovie() {
        return movie;
    }

    public int getYear() {
        return year;
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
