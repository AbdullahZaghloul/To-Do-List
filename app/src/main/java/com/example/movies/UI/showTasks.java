package com.example.movies.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.movies.Adapter.TaskAdapter;
import com.example.movies.R;
import com.example.movies.Room.TaskEntity;
import com.example.movies.ViewModels.TaskViewModel;
import com.example.movies.databinding.ActivityShowTasksBinding;

import java.util.List;

public class showTasks extends AppCompatActivity {

    ActivityShowTasksBinding binding;
    TaskViewModel viewModel;
    TaskAdapter taskAdapter;
    String task_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tasks);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_tasks);
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        task_type = getIntent().getStringExtra("task type");

        binding.showTasksAllTasksTV.setText(task_type);

        binding.showTasksTasksRecyRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        taskAdapter = new TaskAdapter();
        binding.showTasksTasksRecyRecycler.setAdapter(taskAdapter);

        if(task_type.equals("personal")){
            Log.d("personalll", "personalll");
            viewModel.getPersonalmutableLiveData().observe(this, new Observer<List<TaskEntity>>() {
                @Override
                public void onChanged(List<TaskEntity> taskEntities) {
                    Log.d("personalll", "personalll"+taskEntities.size());
                    taskAdapter.setList(taskEntities);
                }
            });
        }else if(task_type.equals("collage")){
            viewModel.getCollagemutableLiveData().observe(this, new Observer<List<TaskEntity>>() {
                @Override
                public void onChanged(List<TaskEntity> taskEntities) {
                    taskAdapter.setList(taskEntities);
                }
            });
        }

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 121:
                TaskEntity task = taskAdapter.removeTask(item.getGroupId());
                viewModel.deleteTask(task);
                showMessage("task Deleted.....");
                return true;
            case 122:
                TaskEntity task2 =  taskAdapter.getTask(item.getGroupId());
                Intent i = new Intent(showTasks.this,updateTask.class);
                i.putExtra("task id",task2.getId());
                i.putExtra("task name",task2.getName());
                i.putExtra("task desc",task2.getDesc());
                i.putExtra("task type",task_type);
                startActivity(i);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}