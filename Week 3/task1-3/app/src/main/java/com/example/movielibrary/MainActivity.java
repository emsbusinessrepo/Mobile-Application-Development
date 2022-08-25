package com.example.movielibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;
//w3
public class MainActivity extends AppCompatActivity {

    String message;
    String genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("lab3", "onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lab3", "onStart");

        SharedPreferences myData = getPreferences(0);
        String msgName = myData.getString("movieName","");
        EditText movieName = findViewById(R.id.titleName);
        movieName.setText(msgName);

        String year = myData.getString("year","");
        EditText yearNumber = findViewById(R.id.year);
        yearNumber.setText(year);

        String msgCountry = myData.getString("country","");
        EditText country = findViewById(R.id.country);
        country.setText(msgCountry);

        String msgGenre = myData.getString("genre","");
        EditText genre = findViewById(R.id.genre);
        genre.setText(msgGenre);

        String cost = myData.getString("cost","");
        EditText costNumber = findViewById(R.id.cost);
        costNumber.setText(cost);

        String msgKeywords = myData.getString("keywords","");
        EditText keyword = findViewById(R.id.keywords);
        keyword.setText(msgKeywords);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lab3", "onResume");
        EditText movieName = findViewById(R.id.titleName);
        SharedPreferences myData = getPreferences(0);
        String movie = myData.getString("movieName", "");
        movieName.setText(movie.toUpperCase());

        EditText movieGenre = findViewById(R.id.genre);
        String genre = myData.getString("genre", "");
        movieGenre.setText(genre.toLowerCase());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lab3", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lab3", "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lab3", "onDestroy");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("lab3", "onSaveInstanceState");

        EditText myMsg = findViewById(R.id.titleName);
        message = myMsg.getText().toString();
        outState.putString("key1",message);

        EditText myGenre = findViewById(R.id.genre);
        genre = myGenre.getText().toString();
        outState.putString("key2",genre);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("lab3", "onRestoreInstanceState");

        message = savedInstanceState.getString("key1").toUpperCase();
        genre = savedInstanceState.getString("key2").toLowerCase(Locale.ROOT);
    }

    public void showToast(View view)
    {
        EditText movieName = findViewById(R.id.titleName);
        String name = movieName.getText().toString().trim();
        String msgName = movieName.getText().toString();

        SharedPreferences myData = getPreferences(0);
        SharedPreferences.Editor myEditorName = myData.edit();
        myEditorName.putString("movieName",msgName);
        myEditorName.commit();

        EditText yearNumber = findViewById(R.id.year);
        String year = yearNumber.getText().toString();

        SharedPreferences.Editor myEditorYear = myData.edit();
        myEditorYear.putString("year",year);
        myEditorYear.commit();

        EditText country = findViewById(R.id.country);
        String msgCountry = country.getText().toString();

        SharedPreferences.Editor myEditorCountry = myData.edit();
        myEditorCountry.putString("country",msgCountry);
        myEditorCountry.commit();

        EditText genre = findViewById(R.id.genre);
        String msgGenre = genre.getText().toString();

        SharedPreferences.Editor myEditorGenre = myData.edit();
        myEditorGenre.putString("genre",msgGenre);
        myEditorGenre.commit();

        EditText costNumber = findViewById(R.id.cost);
        String cost = costNumber.getText().toString();

        SharedPreferences.Editor myEditorCost = myData.edit();
        myEditorCost.putString("cost",cost);
        myEditorCost.commit();

        EditText keyword = findViewById(R.id.keywords);
        String msgKeywords = keyword.getText().toString();

        SharedPreferences.Editor myEditorKeywords = myData.edit();
        myEditorKeywords.putString("keywords",msgKeywords);
        myEditorKeywords.commit();
        Toast myToast = Toast.makeText(this, "Movie - "  + name + " - has been added", Toast.LENGTH_LONG);
        myToast.show();
    }

    public void doubleCost(View view)
    {
        EditText costNumber = findViewById(R.id.cost);
        int cost = Integer.parseInt(costNumber.getText().toString());
        costNumber.setText(Integer.toString(cost * 2));

        Toast myToast = Toast.makeText(this, "The cost of the movie has been doubled", Toast.LENGTH_LONG);
        myToast.show();
    }

    public void resetbutton(View view)
    {
        EditText movieName = findViewById(R.id.titleName);
        movieName.getText().clear();

        EditText year = findViewById(R.id.year);
        year.getText().clear();

        EditText country = findViewById(R.id.country);
        country.getText().clear();

        EditText genre = findViewById(R.id.genre);
        genre.getText().clear();

        EditText keyword = findViewById(R.id.keywords);
        keyword.getText().clear();

        EditText costNumber = findViewById(R.id.cost);
        costNumber.getText().clear();

        Toast myToast = Toast.makeText(this, "The movie library has been reset", Toast.LENGTH_LONG);
        myToast.show();
    }


    public void clearPreferencesButton(View view)
    {
        Toast myToast = Toast.makeText(this, "Clearing Preferences", Toast.LENGTH_LONG);
        myToast.show();


        SharedPreferences myData = getPreferences(0);
        SharedPreferences.Editor myEditorName = myData.edit();
        myEditorName.clear();
        myEditorName.commit();
    }

    public void loadButton(View view)
    {
        //load the cost
        SharedPreferences myData = getPreferences(0);
        String msg = myData.getString("cost", " ");
        int cost = Integer.parseInt(msg);


        SharedPreferences myData2 = getPreferences(0);
        SharedPreferences.Editor myEditorCost2 = myData2.edit();
        myEditorCost2.putString("cost", Integer.toString(cost *2));
        myEditorCost2.commit();

        EditText costMsg = findViewById(R.id.cost);
        costMsg.setText(msg);



    }



}