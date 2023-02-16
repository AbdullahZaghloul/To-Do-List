package com.example.movies;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class aboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        final RelativeLayout ll=(RelativeLayout) findViewById(R.id.Relative);
        final RadioButton radio_red = (RadioButton) findViewById(R.id.radio_red);
        final RadioButton radio_green = (RadioButton) findViewById(R.id.radio_green);
        final RadioButton radio_blue = (RadioButton) findViewById(R.id.radio_blue);
        radio_red.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ll.setBackgroundColor(Color.RED);

            }
        });
        radio_blue.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ll.setBackgroundColor(Color.BLUE);

            }
        });
        radio_green.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ll.setBackgroundColor(Color.GREEN);

            }
        });

        String [] arr =getResources().getStringArray(R.array.Grid);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr);
        GridView mygrid =(GridView) findViewById(R.id.mygridview1);
        mygrid.setAdapter(adapter);


    }
}