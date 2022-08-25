package com.example.week01t3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMessage(View view)
    {
        TextView myTextView = findViewById(R.id.textView);
        myTextView.setText("Mobile Application Development");

        TextView myTextView2 = findViewById(R.id.textView2);
        myTextView2.setText("FIT2081");

        TextView myTextView3 = findViewById(R.id.textView3);
        myTextView3.setText("Android Studio");
    }

}