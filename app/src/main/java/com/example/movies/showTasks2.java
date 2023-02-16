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

public class showTasks2 extends AppCompatActivity {

    ListView tasksList2;
    ArrayAdapter<String> adapter2;
    taskDBHelper tasks2 = new taskDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tasks2);
        tasksList2 = (ListView) findViewById(R.id.listview_alltasks2_id);
        adapter2 = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1);
        tasksList2.setAdapter(adapter2);
        registerForContextMenu(tasksList2);

        Cursor cursor = tasks2.fetchAllTasks2();

        while(!cursor.isAfterLast())
        {
            adapter2.add(cursor.getString(0));
            cursor.moveToNext();
        }

        tasksList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView descTxt = (TextView)findViewById(R.id.txt_desc2);

                String desc =  tasks2.getTaskDesc2(((TextView)view).getText().toString());
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
        String selectedMovieName = ((TextView)info.targetView).getText().toString();
        int id = item.getItemId();
        if(id == R.id.item_delete)
        {
            adapter2.remove(selectedMovieName);
            tasks2.deleteTask2(selectedMovieName);
            Toast.makeText(this, "Task Deleted", Toast.LENGTH_SHORT).show();
            return true;

        }
        if(id == R.id.item_update)
        {
            Intent intent = new Intent(showTasks2.this , updateTask2.class);
            intent.putExtra("taskName2" , selectedMovieName);
            intent.putExtra("taskDesc2" , tasks2.getTaskDesc2(selectedMovieName));
            startActivity(intent);
            return true;
        }

        return super.onContextItemSelected(item);
    }
}
