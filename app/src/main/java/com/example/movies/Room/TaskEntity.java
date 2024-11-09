package com.example.movies.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Task Table")
public class TaskEntity {
    @PrimaryKey(autoGenerate = true)
    private int id ;
    private String name;
    private String desc;

    private String type;

    public TaskEntity(String name, String desc, String type) {
        this.name = name;
        this.desc = desc;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }
}
