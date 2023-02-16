package com.example.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText desc;
    Button addTask;
    Button showAllTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.task_name_id);
        desc = (EditText) findViewById(R.id.task_des_id);
        addTask = (Button) findViewById(R.id.btn_add_task);
        showAllTasks = (Button)findViewById(R.id.btn_show_all_Tasks);
        taskDBHelper newTask = new taskDBHelper(this);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("") && desc.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Task Name and Description", Toast.LENGTH_SHORT).show();
                }
                else if(name.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Task Name", Toast.LENGTH_SHORT).show();
                }
                else if(desc.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please Enter Task Description", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    newTask.createNewTask(name.getText().toString(), desc.getText().toString());
                    name.setText("");
                    desc.setText("");
                    Toast.makeText(MainActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showAllTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , showTasks.class);
                startActivity(intent);
            }
        });
    }
}
