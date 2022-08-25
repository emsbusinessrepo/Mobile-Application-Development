package com.example.weatherlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private WeatherApp weatherValue;
    private EditText weather_locationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weather_locationID = findViewById(R.id.locationID);
    }

    public void onClick(View v) {
        makeRequest();
    }

    private void makeRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String locationString = weather_locationID.getText().toString();
        String url = "https://api.weatherapi.com/v1/current.json?key=cde75afa40ce4179b2781925223003&q=" + locationString;

        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                TextView temperatureText = findViewById(R.id.tempID);
                                TextView precipitationText = findViewById(R.id.preciID);
                                TextView humidityText = findViewById(R.id.humidityID);
                                TextView windText = findViewById(R.id.windID);

                                String temperature;
                                String wind;
                                String precipitation;
                                String humidity;
                                try {
                                    JSONObject listItems = response.getJSONObject("current");
                                    temperature = listItems.getString("temp_c");
                                    wind = listItems.getString("wind_kph");
                                    precipitation = listItems.getString("precip_mm");
                                    humidity = listItems.getString("humidity");
                                    weatherValue = new WeatherApp(temperature, precipitation, humidity, wind);

                                    temperatureText.setText(weatherValue.getTemp());
                                    precipitationText.setText(weatherValue.getPrecipitation());
                                    humidityText.setText(weatherValue.getHumidity());
                                    windText.setText(weatherValue.getWind());
                                } catch (Exception e) {
                                    Log.d("weather", e.getMessage());
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("weather", error.getMessage());
                    }
                });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
}