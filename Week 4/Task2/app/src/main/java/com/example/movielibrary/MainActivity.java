package com.example.movielibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.StringTokenizer;
//w4
public class MainActivity extends AppCompatActivity {

    EditText movieName, year, country, genre, cost, keywords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieName = findViewById(R.id.titleNameID);
        year = findViewById(R.id.yearID);
        country = findViewById(R.id.countryID);
        genre = findViewById(R.id.genreID);
        cost = findViewById(R.id.costID);
        keywords = findViewById(R.id.keywordsID);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);

        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();

        registerReceiver(myBroadCastReceiver, new IntentFilter(SMSReciever.SMS_FILTER));
    }
    class MyBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra(SMSReciever.SMS_MSG_KEY);
            StringTokenizer sT = new StringTokenizer(msg, ";");

            String movieNameA = sT.nextToken();
            String yearA = sT.nextToken();
            String countryA = sT.nextToken();
            String genreA = sT.nextToken();
            String costA = sT.nextToken();
            String keywordsA = sT.nextToken();
            String hiddenFeas = sT.nextToken();

            movieName.setText(movieNameA);
            year.setText(yearA);
            country.setText(countryA);
            genre.setText(genreA);
            cost.setText(costA);
            keywords.setText(keywordsA);
            int costs = Integer.parseInt(costA);
            int hidden = Integer.parseInt(hiddenFeas);
            cost.setText(Integer.toString(hidden+costs));
        }
    }

    public void showToast(View view)
    {
        EditText movieName = findViewById(R.id.titleNameID);
        String name = movieName.getText().toString().trim();
        Toast myToast = Toast.makeText(this, "Movie - "  + name + " - has been added", Toast.LENGTH_LONG);
        myToast.show();
    }

    public void doubleCost(View view)
    {
        EditText costNumber = findViewById(R.id.costID);
        int cost = Integer.parseInt(costNumber.getText().toString());
        costNumber.setText(Integer.toString(cost * 2));

        Toast myToast = Toast.makeText(this, "The cost of the movie has been doubled", Toast.LENGTH_LONG);
        myToast.show();
    }

    public void resetbutton(View view)
    {
        EditText movieName = findViewById(R.id.titleNameID);
        movieName.getText().clear();

        EditText year = findViewById(R.id.yearID);
        year.getText().clear();

        EditText country = findViewById(R.id.countryID);
        country.getText().clear();

        EditText genre = findViewById(R.id.genreID);
        genre.getText().clear();

        EditText keyword = findViewById(R.id.keywordsID);
        keyword.getText().clear();

        EditText costNumber = findViewById(R.id.costID);
        costNumber.getText().clear();

        Toast myToast = Toast.makeText(this, "The movie library has been reset", Toast.LENGTH_LONG);
        myToast.show();
    }
}