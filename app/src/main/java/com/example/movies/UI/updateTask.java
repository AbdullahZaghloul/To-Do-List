package com.example.movies.UI;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.movies.R;
import com.example.movies.Room.TaskEntity;
import com.example.movies.ViewModels.TaskViewModel;
import com.example.movies.databinding.ActivityUpdateTaskBinding;

public class updateTask extends AppCompatActivity {

    ActivityUpdateTaskBinding binding;
    String task_type,task_name,task_desc;
    int task_id;
    TaskViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_task);
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        task_id = getIntent().getIntExtra("task id",0);
        task_name = getIntent().getStringExtra("task name");
        task_desc = getIntent().getStringExtra("task desc");
        task_type = getIntent().getStringExtra("task type");

        binding.updateUpdateTv.setText(task_type);
        binding.updateTaskNameEd.setText(task_name);
        binding.updateTaskDescEt.setText(task_desc);

        binding.updateUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskEntity newTask = new TaskEntity(binding.updateTaskNameEd.getText().toString()
                        ,binding.updateTaskDescEt.getText().toString()
                        ,task_type);
                newTask.setId(task_id);
                viewModel.updateTask(newTask);
                finish();
            }
        });




    }
}