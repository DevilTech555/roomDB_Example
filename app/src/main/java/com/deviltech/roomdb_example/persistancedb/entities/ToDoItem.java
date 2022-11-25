package com.deviltech.roomdb_example.persistancedb.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ToDoItem {

    @PrimaryKey(autoGenerate = true)
    public int ID;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "isCompleted")
    public Boolean isCompleted;

    public ToDoItem(String description, Boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
