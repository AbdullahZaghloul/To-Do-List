package com.example.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    EditText name2;
    EditText desc2;
    Button addTask2;
    Button showAllTasks2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name2 = (EditText) findViewById(R.id.task_name2_id);
        desc2 = (EditText) findViewById(R.id.task_des2_id);
        addTask2 = (Button) findViewById(R.id.btn_add_task2);
        showAllTasks2 = (Button)findViewById(R.id.btn_show_all_Tasks2);
        taskDBHelper newTask2 = new taskDBHelper(this);

        addTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name2.getText().toString().equals("") && desc2.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity2.this, "Please Enter Task Name and Description", Toast.LENGTH_SHORT).show();
                }
                else if(name2.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity2.this, "Please Enter Task Name", Toast.LENGTH_SHORT).show();
                }
                else if(desc2.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity2.this, "Please Enter Task Description", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    newTask2.createNewTask2(name2.getText().toString(), desc2.getText().toString());
                    name2.setText("");
                    desc2.setText("");
                    Toast.makeText(MainActivity2.this, "Task Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showAllTasks2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this , showTasks2.class);
                startActivity(intent);
            }
        });

    }
}