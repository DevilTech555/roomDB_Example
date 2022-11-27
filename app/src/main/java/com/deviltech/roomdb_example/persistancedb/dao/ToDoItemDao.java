package com.deviltech.roomdb_example.persistancedb.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.deviltech.roomdb_example.persistancedb.entities.ToDoItem;

import java.util.List;

@Dao
public interface ToDoItemDao {

    @Query("SELECT * FROM todoitem")
    List<ToDoItem> getAll();

    @Insert
    void insertAll(ToDoItem... doItems);

    @Insert
    void insert(ToDoItem toDoItem);

    @Delete
    void delete(ToDoItem toDoItem);

    @Query("DELETE FROM todoitem")
    void deleteAll();

    @Update
    void update(ToDoItem toDoItem);
}
