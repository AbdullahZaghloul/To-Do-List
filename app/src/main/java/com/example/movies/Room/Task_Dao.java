package com.example.movies.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface Task_Dao {

    @Insert
    Completable insertTask(TaskEntity task);

    @Delete
    Completable deleteTask(TaskEntity task);

    @Update
    Completable updateTask(TaskEntity task);


    @Query("select * from `task table` where type = 'collage' order by id asc")
    Flowable<List<TaskEntity>> getCollageTask();

    @Query("select * from `task table` where type = 'personal' order by id asc")
    Flowable<List<TaskEntity>> getPersonalTask();
}
