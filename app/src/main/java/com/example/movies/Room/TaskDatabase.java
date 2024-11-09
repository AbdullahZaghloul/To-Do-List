package com.example.movies.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {TaskEntity.class},version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase taskDatabase;

    public abstract Task_Dao taskDao();

    public static synchronized TaskDatabase getInstance(Context context) {
        if (taskDatabase == null) {
            taskDatabase = Room.databaseBuilder(context.getApplicationContext()
                    , TaskDatabase.class, "task database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return taskDatabase;
    }
}
