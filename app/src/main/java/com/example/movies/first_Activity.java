package com.example.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class first_Activity extends AppCompatActivity {

    Spinner my;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        my=(Spinner) findViewById(R.id.myspinner);
        my.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selecteditem =parent.getItemAtPosition(position).toString();

               /* if (parent.getItemAtPosition(position).equals("Choose Something")) {
                    Toast.makeText(first_Activity.this, "None", Toast.LENGTH_SHORT).show();
                }*/

                if (selecteditem.equals("Personal") || selecteditem.equals("شخصي") ) {
                    Intent out2 = new Intent(first_Activity.this, MainActivity2.class);
                    startActivity(out2);
                }

                else if (selecteditem.equals("College") || selecteditem.equals("كليه")) {
                    Intent out3 = new Intent(first_Activity.this, MainActivity.class);
                    startActivity(out3);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.language_id:
                Intent i = new Intent(first_Activity.this , language.class);
                startActivity(i);
                return true;
            case  R.id.AboutUs_id:
                Intent i2 = new Intent(first_Activity.this , aboutUs.class);
                startActivity(i2);
                return true;
        }
        return false;

    }
}