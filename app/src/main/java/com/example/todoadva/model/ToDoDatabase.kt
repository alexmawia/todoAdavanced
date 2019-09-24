package com.example.todoadva.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun todoDao(): ToDoDao

    companion object{
        @Volatile
        var database: ToDoDatabase? =  null

        fun getInstance(context: Context): ToDoDatabase? {
            if (database == null){
                synchronized(ToDoDatabase::class.java){
                    if (database == null){
                        database = Room.databaseBuilder(context.applicationContext, ToDoDatabase::class.java, "todo_table").build()
                    }
                }
            }
            return database
        }
    }
}