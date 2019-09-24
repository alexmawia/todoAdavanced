package com.example.todoadva.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todoadva.model.ToDo
import com.example.todoadva.model.ToDoRepository

class ToDoViewModel(application: Application): AndroidViewModel(application) {

    private val toDoRepository:ToDoRepository
    private val allToDo: LiveData<List<ToDo>>

    init {
        toDoRepository = ToDoRepository(application)
        allToDo = toDoRepository.getAllTodos()

    }

    fun insertToDo(todo:ToDo){
        toDoRepository.insertToDo(todo)
    }
    fun  deleteTodo(todo:ToDo){
        toDoRepository.deleteTodo(todo)

    }
    fun deleteAllTodos(){
        toDoRepository.deleteAllTodos()
    }

    fun getAllTodos() = allToDo

    fun getTodoByName(Title:String): ToDo?{
        return toDoRepository.getTodoByName(Title)
    }


}