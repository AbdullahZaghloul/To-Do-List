package com.example.movies.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movies.R;
import com.example.movies.databinding.ActivityFirstBinding;

public class first_Activity extends AppCompatActivity {

    ActivityFirstBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first);

        binding.firstActivityPersonalCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(first_Activity.this, MainActivity.class);
                intent.putExtra("task type", getString(R.string.task_type_personal));
                startActivity(intent);
            }
        });

        binding.firstActivityCollageCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(first_Activity.this, MainActivity.class);
                intent.putExtra("task type", getString(R.string.task_type_collage));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.optionMenu_item_language){
            startActivity(new Intent(first_Activity.this,language.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}