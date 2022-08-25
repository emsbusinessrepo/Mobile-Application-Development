package com.example.weatherlab;
// Week 6
public class WeatherApp {
    private String temp;
    private String precipitation;
    private String humidity;
    private String wind;

    public WeatherApp(String temp, String precipitation, String humidity, String wind) {
        this.temp = temp;
        this.precipitation = precipitation;
        this.humidity = humidity;
        this.wind = wind;
    }

    public String getTemp() {
        return temp;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPrecipitation() {
        return precipitation + "%";
    }
    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getHumidity() {
        return humidity + "%";
    }
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind() {
        return wind;
    }
    public void setWind(String wind) {
        this.wind = wind;
    }
}
