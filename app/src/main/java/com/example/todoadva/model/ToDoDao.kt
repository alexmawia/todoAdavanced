package com.example.todoadva.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertToDo(todo:ToDo)

    @Delete
    fun  deleteTodo(todo:ToDo)

    @Query("DELETE FROM todo_table")
    fun deleteAllTodos()


    @Query( "SELECT * FROM todo_table")
    fun getAllTodos(): LiveData<List<ToDo>>

    @Query ("SELECT * FROM todo_table WHERE Title= :Title")
    fun getTodoByName(Title:String): ToDo




}