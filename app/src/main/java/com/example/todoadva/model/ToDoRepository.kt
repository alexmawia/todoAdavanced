package com.example.todoadva.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.room.Delete

class ToDoRepository(application: Application) {

    private val toDoDao:ToDoDao?
    private val allTodos: LiveData<List<ToDo>>?

    init {
        val db = ToDoDatabase.getInstance(application)
        toDoDao = db?.todoDao()
        allTodos = toDoDao?.getAllTodos()
    }


    fun insertToDo(todo:ToDo){
        InsertAsyncTask(toDoDao!!).execute(todo)
    }


    fun  deleteTodo(todo:ToDo){
        DeleteAsyncTask(toDoDao!!).execute(todo)
    }
    fun deleteAllTodos(){
        DeleteAllAsyncTask(toDoDao!!).execute()
    }
    fun getAllTodos(): LiveData<List<ToDo>>{
        return allTodos!!
    }
    fun getTodoByName(Title:String): ToDo?{
        val allToDoList = allTodos?.value?.toList()
        allToDoList?.iterator()?.forEach {
            if (it.Title == Title) {
                return it

            }
        }
        return null

    }
    //changes manually
    private class InsertAsyncTask(private val dao:ToDoDao): AsyncTask<ToDo, Void, Void>() {
        override fun doInBackground(vararg params: ToDo): Void? {
            dao.insertToDo(params[0])
            return null
        }

    }


    private class DeleteAsyncTask(private val dao:ToDoDao): AsyncTask<ToDo, Void, Void>() {
        override fun doInBackground(vararg params: ToDo): Void? {
            dao.deleteTodo(params[0])
            return null
        }

    }

    private class DeleteAllAsyncTask(private val dao:ToDoDao): AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            dao.deleteAllTodos()
            return null
        }

    }

}