package com.example.movies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class taskDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "taskDatabase";
    SQLiteDatabase taskDatabase;

    public taskDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table CollegeTask (id integer primary key, " +
                "name text not null, description text not null)");
        sqLiteDatabase.execSQL("create table PersonalTask (id integer primary key, " +
                "name text not null, description text not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists CollegeTask");
        sqLiteDatabase.execSQL("drop table if exists PersonalTask");
        onCreate(sqLiteDatabase);

    }
    public void createNewTask(String name , String Desc)
    {
        ContentValues values  = new ContentValues();
        values.put("name" , name);
        values.put("description" , Desc);
        taskDatabase = getWritableDatabase();
        taskDatabase.insert("CollegeTask" , null , values );
        taskDatabase.close();
    }
    public Cursor fetchAllTasks()
    {
        taskDatabase = getReadableDatabase();
        Cursor cursor = taskDatabase.query("CollegeTask" , new String[]{"name" , "description" , "id"}  ,
                null, null , null, null , null);
        if(cursor!=null)
            cursor.moveToFirst();
        taskDatabase.close();
        return cursor;
    }
    public String getTaskDesc(String name)
    {
        taskDatabase = getReadableDatabase();
        Cursor cursor = taskDatabase.rawQuery("select description from CollegeTask where name like ?" , new String[]{name});
        cursor.moveToFirst();
        taskDatabase.close();
        return cursor.getString(0);
    }
    public void deleteTask(String name)
    {
        taskDatabase = getWritableDatabase();
        taskDatabase.delete("CollegeTask" , "name like ?" , new String[]{name});
        taskDatabase.close();
    }
    public void updateTask(String oldName , String newName , String newDesc)
    {
        taskDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name" , newName);
        values.put("description" , newDesc);
        taskDatabase.update("CollegeTask" , values , "name like ?" , new String []{oldName} );
        taskDatabase.close();
    }

    public void createNewTask2(String name , String Desc)
    {
        ContentValues values  = new ContentValues();
        values.put("name" , name);
        values.put("description" , Desc);
        taskDatabase = getWritableDatabase();
        taskDatabase.insert("PersonalTask" , null , values );
        taskDatabase.close();
    }
    public Cursor fetchAllTasks2()
    {
        taskDatabase = getReadableDatabase();
        Cursor cursor = taskDatabase.query("PersonalTask" , new String[]{"name" , "description" , "id"}  ,
                null, null , null, null , null);
        if(cursor!=null)
            cursor.moveToFirst();
        taskDatabase.close();
        return cursor;
    }
    public String getTaskDesc2(String name)
    {
        taskDatabase = getReadableDatabase();
        Cursor cursor = taskDatabase.rawQuery("select description from PersonalTask where name like ?" , new String[]{name});
        cursor.moveToFirst();
        taskDatabase.close();
        return cursor.getString(0);
    }
    public void deleteTask2(String name)
    {
        taskDatabase = getWritableDatabase();
        taskDatabase.delete("PersonalTask" , "name like ?" , new String[]{name});
        taskDatabase.close();
    }
    public void updateTask2(String oldName , String newName , String newDesc)
    {
        taskDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name" , newName);
        values.put("description" , newDesc);
        taskDatabase.update("PersonalTask" , values , "name like ?" , new String []{oldName} );
        taskDatabase.close();
    }



}
