package com.example.movielibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view)
    {
        EditText movieName = findViewById(R.id.titleName);
        String name = movieName.getText().toString().trim();
        Toast myToast = Toast.makeText(this, "Movie - "  + name + " - has been added",
                Toast.LENGTH_LONG);
        myToast.show();
    }

    public void doubleCost(View view)
    {
        EditText costNumber = findViewById(R.id.cost);
        int cost = Integer.parseInt(costNumber.getText().toString());
        costNumber.setText(Integer.toString(cost * 2));

        Toast myToast = Toast.makeText(this, "The cost of the movie has been doubled",
                Toast.LENGTH_LONG);
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

        Toast myToast = Toast.makeText(this, "The movie library has been reset",
                Toast.LENGTH_LONG);
        myToast.show();
    }
}