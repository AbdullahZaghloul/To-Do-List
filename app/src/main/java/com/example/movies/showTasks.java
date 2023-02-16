package com.example.movies;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class showTasks extends AppCompatActivity {

    ListView tasksList;
    ArrayAdapter<String> adapter;
    taskDBHelper Tasks = new taskDBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tasks);
        tasksList = (ListView) findViewById(R.id.listview_alltasks_id);
        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1);
        tasksList.setAdapter(adapter);
        registerForContextMenu(tasksList);

        Cursor cursor = Tasks.fetchAllTasks();

        while(!cursor.isAfterLast())
        {
            adapter.add(cursor.getString(0));
            cursor.moveToNext();
        }

        tasksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView descTxt = (TextView)findViewById(R.id.txt_desc);

                String desc =  Tasks.getTaskDesc(((TextView)view).getText().toString());
                descTxt.setText(desc);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu );
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info
                = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        String selectedTaskName = ((TextView)info.targetView).getText().toString();
        int id = item.getItemId();
        if(id == R.id.item_delete)
        {
            adapter.remove(selectedTaskName);
            Tasks.deleteTask(selectedTaskName);
            Toast.makeText(this, "Task Deleted", Toast.LENGTH_SHORT).show();
            return true;

        }
        if(id == R.id.item_update)
        {
            Intent intent = new Intent(showTasks.this , updateTask.class);
            intent.putExtra("taskName" , selectedTaskName);
            intent.putExtra("taskDesc" , Tasks.getTaskDesc(selectedTaskName));
            startActivity(intent);
            return true;
        }

        return super.onContextItemSelected(item);
    }

}