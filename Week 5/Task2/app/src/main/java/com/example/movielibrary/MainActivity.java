package com.example.movielibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


import java.util.ArrayList;
import java.util.StringTokenizer;
// Week 5
public class MainActivity extends AppCompatActivity {

    EditText movieName, year, country, genre, cost, keywords;

    ArrayList<String> myList = new ArrayList<>();
    ArrayAdapter myAdapter;
    int count = 0;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_movie);

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

        //Week 5 *****************************************

        Toolbar toolbar = findViewById(R.id.toolbar_movieID);
        setSupportActionBar(toolbar);

        ListView listView = findViewById(R.id.listviewID);
        myAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList);
        listView.setAdapter(myAdapter);

        drawer = findViewById(R.id.dl);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationViewID);
        navigationView.setNavigationItemSelectedListener(new MyNavigationListener());

        FloatingActionButton fab = findViewById(R.id.floatingActionButtonID);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewMovie();
            }
        });

    }

    private void addNewMovie(){
        Toast.makeText(getApplicationContext(), "Movie - " + movieName.getText().toString() + " - has been added",
                Toast.LENGTH_LONG).show();
        myList.add(movieName.getText().toString() + " | " + year.getText().toString());
        myAdapter.notifyDataSetChanged();
    }

    public void clearAllFields(){
        movieName.setText("");
        year.setText("");
        country.setText("");
        genre.setText("");
        cost.setText("");
        keywords.setText("");
    }


    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item){
            int id = item.getItemId();
            if (id == R.id.addmovieID) {
                addNewMovie();
            } else if (id == R.id.removeLastMovieID) {
                myList.remove(myList.size()-1);
                myAdapter.notifyDataSetChanged();
            } else if (id == R.id.removeAllMoviesID) {
                myList.clear();
                myAdapter.notifyDataSetChanged();
            } else if(id == R.id.closeID) {
                finish();
            }
            drawer.closeDrawers();
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.clearFieldsID) {
            clearAllFields();
            Toast.makeText(getApplicationContext(), "All fields have been removed.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.totalMoviesID) {
            Toast.makeText(getApplicationContext(),"Total movie(s): " + myList.size(),Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addMovieButton(View view)
    {
        addNewMovie();
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