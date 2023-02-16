package com.example.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class updateTask2 extends AppCompatActivity {

    EditText newName2;
    EditText newDesc2;
    Button btnupdate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task2);
        newName2 =(EditText) findViewById(R.id.NewNameTask2_id);
        newDesc2 = (EditText) findViewById(R.id.newTaskDesc2_id);
        btnupdate2 = (Button) findViewById(R.id.btnUpdate2_id);
        taskDBHelper task2 = new taskDBHelper(this);

        newName2.setText(getIntent().getExtras().getString("taskName2"));
        newDesc2.setText(getIntent().getExtras().getString("taskDesc2"));

        btnupdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task2.updateTask2(getIntent().getExtras().getString("taskName2") , newName2.getText().toString() , newDesc2.getText().toString() );
                Toast.makeText(updateTask2.this, "Task Updated", Toast.LENGTH_SHORT).show();
                Intent refresh = new Intent(updateTask2.this , showTasks2.class);
                startActivity(refresh);
                finish();

            }


        });


    }
}