package com.example.todoadva.view

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoadva.R
import com.example.todoadva.model.ToDo
import com.example.todoadva.utils.*
import com.example.todoadva.viewmodel.ToDoViewModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ToDoListAdapter.ItemClickListener {
    override fun onItemClick(view: View, position: Int) {

        //update activity
        val intent = Intent(this, AddTask::class.java )
        intent.putExtra(EXTRA_KEY_TITLE, mAdapter.getTodos()[position].Title)
        intent.putExtra(EXTRA_KEY_DESC, mAdapter.getTodos()[position].Descrption)
        intent.putExtra(EXTRA_KEY_DATE, mAdapter.getTodos()[position].Date)
        startActivityForResult(intent, ADD_TASK_REQUEST_CODE)
    }

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: ToDoListAdapter
    private var mToDo: List<ToDo> = mutableListOf<ToDo>()

    private lateinit var mToDoViewHolder: ToDoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mRecyclerView = findViewById(R.id.recyclerView)
        mAdapter = ToDoListAdapter(this, this)
        mAdapter.setTodos(mToDo)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        mToDoViewHolder = ViewModelProviders.of(this).get(ToDoViewModel::class.java)

        mToDoViewHolder.getAllTodos().observe(this, Observer { words ->
            words?.let{
mAdapter.setTodos(it)
            }


        })

        fab.setOnClickListener { view ->
            val intent = Intent(this, AddTask::class.java )
            startActivityForResult(intent, ADD_TASK_REQUEST_CODE)
                    }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ADD_TASK_REQUEST_CODE && resultCode == RESULT_SAVE){
           data?.let {
               val toDo = ToDo(it.getStringExtra(EXTRA_KEY_TITLE), it.getStringExtra(EXTRA_KEY_DESC), it.getStringExtra(
                   EXTRA_KEY_DATE))
               mToDoViewHolder.insertToDo(toDo)
           }
        }
        else if (requestCode == ADD_TASK_REQUEST_CODE && resultCode == RESULT_SAVE){
                Toast.makeText(this, "empty word not saved", Toast.LENGTH_LONG).show()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
