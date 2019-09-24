package com.example.todoadva.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoadva.R
import com.example.todoadva.model.ToDo

class ToDoListAdapter(private val mcontext:Context, private var mItemClickListener: ItemClickListener): RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder>() {

    private lateinit var mTodo: List<ToDo>

    fun getTodos() = mTodo

    fun setTodos(todos: List<ToDo>){
        mTodo = todos
        notifyDataSetChanged()
    }

    //update function
    interface ItemClickListener{
        fun onItemClick(view: View, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mTodo.size

    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        var currentToDo = mTodo[position]
        holder.todoTextView.text = currentToDo.Title
        holder.descrTextView.text = currentToDo.Descrption
        holder.dateTextView.text = currentToDo.Date

        holder.todoTextView.setOnClickListener{
            mItemClickListener.onItemClick(holder.todoTextView, position)
        }
    }

    class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var todoTextView: TextView = itemView.findViewById(R.id.textView)
        var descrTextView: TextView = itemView.findViewById(R.id.Descrption)
        var dateTextView: TextView = itemView.findViewById(R.id.Date)
    }
}