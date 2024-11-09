package com.example.movies.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.movies.R;
import com.example.movies.Room.TaskEntity;
import com.example.movies.ViewModels.TaskViewModel;
import com.example.movies.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    TaskViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);


        String task_type = getIntent().getStringExtra("task type");

        binding.mainHeaderTv.setText(task_type);

        binding.mainAddTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.mainTaskNameEt.getText().toString().isEmpty() && binding.mainTaskDescEt.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, getString(R.string.name_description_is_empty), Toast.LENGTH_SHORT).show();
                }else if(binding.mainTaskDescEt.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, getString(R.string.description_is_empty), Toast.LENGTH_SHORT).show();
                } else if (binding.mainTaskNameEt.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, getString(R.string.name_is_empty), Toast.LENGTH_SHORT).show();
                }
                else {
                    viewModel.insertTask(new TaskEntity(binding.mainTaskNameEt.getText().toString()
                            , binding.mainTaskDescEt.getText().toString()
                            , task_type));
                    Toast.makeText(MainActivity.this, getString(R.string.Task_added_Successfully),Toast.LENGTH_LONG).show();
                }

            }
        });
        binding.mainShowAllTasksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,showTasks.class);
                i.putExtra("task type",task_type);
                startActivity(i);
            }
        });

    }
}
