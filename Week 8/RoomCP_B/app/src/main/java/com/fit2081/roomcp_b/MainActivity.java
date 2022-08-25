package com.fit2081.roomcp_b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView tV;
    Uri uri;
    Cursor result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tV=findViewById(R.id.textView_id);
        uri= Uri.parse("content://fit2081.app.enming/movies");
    }

    public void getMovieNoButton (View v) {
        result= getContentResolver().query(uri,null,null,null);
        tV.setText(result.getCount()+"");
    }

    public void removeMovieButton(View v) {
        int values = getContentResolver().delete(uri, "costId < 200", null);
        Toast.makeText(v.getContext(), values + " movies deleted", Toast.LENGTH_SHORT).show();
    }

    public void addMovieButton(View v) {
        ContentValues values = new ContentValues();
        values.put("movieId", "Spiderman");
        values.put("yearId", 2022);
        values.put("countryId", "USA");
        values.put("genreId", "Action");
        values.put("costId", 250);
        values.put("keywordsId", "pop");
        Uri uri2 = getContentResolver().insert(uri, values);
        Toast.makeText(this,uri2.toString(),Toast.LENGTH_LONG).show();
    }

    public void updateCostButton(View v) {
        ContentValues values = new ContentValues();
        values.put("costId", 300);
        getContentResolver().update(uri, values,"costId < 200", null);
//        String selectedColumn[] = {"costId"};
//        Cursor item = getContentResolver().query(uri,selectedColumn,null,null);
//        item.moveToFirst();
//        String cost;
//        for (int i = 0; i < item.getCount(); i++){
//            if (i > 100){
//                break;
//            }
//            else{
////                cost = item.getString(item.getColumnIndexOrThrow("costId"));
//                cost = "200";
//                ContentValues values = new ContentValues();
//                getContentResolver().update(uri, values, "costId=" + cost, null);
//
//            }
//
//        }

    }
//    private void updateCost(int cost){
//
//        getContentResolver().update(uri, "costId="+cost, null);
//    }
}