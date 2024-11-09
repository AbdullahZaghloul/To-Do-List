package com.example.movies.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.movies.Room.TaskDatabase;
import com.example.movies.Room.TaskEntity;
import com.example.movies.Room.Task_Dao;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TaskViewModel extends AndroidViewModel {
    private Task_Dao taskDao;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<TaskEntity>> personalmutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<TaskEntity>> collagemutableLiveData = new MutableLiveData<>();

    public TaskViewModel(@NonNull Application application) {
        super(application);
        TaskDatabase taskDatabase = TaskDatabase.getInstance(application);
        taskDao = taskDatabase.taskDao();

    }

    public void getPersonalTask() {
        compositeDisposable.add(taskDao.getPersonalTask().subscribeOn(Schedulers.io()).subscribe(tasks -> personalmutableLiveData.postValue(tasks)));
    }

    public void getCollageTask() {
        compositeDisposable.add(taskDao.getCollageTask().subscribeOn(Schedulers.io()).subscribe(tasks -> collagemutableLiveData.postValue(tasks)));
    }

    public void insertTask(TaskEntity task) {
        compositeDisposable.add(taskDao.insertTask(task).subscribeOn(Schedulers.io()).subscribe());
    }

    public void deleteTask(TaskEntity task) {
        compositeDisposable.add(taskDao.deleteTask(task).subscribeOn(Schedulers.io()).subscribe());
    }

    public void updateTask(TaskEntity task){
        compositeDisposable.add(taskDao.updateTask(task).subscribeOn(Schedulers.io()).subscribe());
    }

    public MutableLiveData<List<TaskEntity>> getPersonalmutableLiveData() {
        return personalmutableLiveData;
    }

    public MutableLiveData<List<TaskEntity>> getCollagemutableLiveData() {
        return collagemutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
