package com.example.todoadva.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class ToDo (@PrimaryKey @NonNull val Title : String, val Descrption: String, val Date: String){

}