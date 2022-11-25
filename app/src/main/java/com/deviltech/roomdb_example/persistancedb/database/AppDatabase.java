package com.deviltech.roomdb_example.persistancedb.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.deviltech.roomdb_example.persistancedb.dao.ToDoItemDao;
import com.deviltech.roomdb_example.persistancedb.entities.ToDoItem;

@Database(entities = {ToDoItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ToDoItemDao toDoItemDao();

    public static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (null == INSTANCE) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "USER_DB")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}