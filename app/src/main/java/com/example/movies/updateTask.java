package com.example.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class updateTask extends AppCompatActivity {

   EditText newName;
   EditText newDesc;
   Button btnupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        newName =(EditText) findViewById(R.id.NewNameTask_id);
        newDesc = (EditText) findViewById(R.id.newTaskDesc_id);
        btnupdate = (Button) findViewById(R.id.btnUpdate_id);
        taskDBHelper task = new taskDBHelper(this);

        newName.setText(getIntent().getExtras().getString("taskName"));
        newDesc.setText(getIntent().getExtras().getString("taskDesc"));

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.updateTask(getIntent().getExtras().getString("taskName") , newName.getText().toString() , newDesc.getText().toString() );
                Toast.makeText(updateTask.this, "Task Updated", Toast.LENGTH_SHORT).show();
                Intent refresh = new Intent(updateTask.this , showTasks.class);
                startActivity(refresh);
                finish();

            }


        });


    }
}