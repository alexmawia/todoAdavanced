package com.example.todoadva.view

import android.content.Intent
import android.content.RestrictionsManager.RESULT_ERROR
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.todoadva.R
import com.example.todoadva.utils.*

class AddTask : AppCompatActivity() {

    private lateinit var TitleEditText:EditText
    private lateinit var DescEditText:EditText
    private lateinit var DateEditText:EditText
    private lateinit var okbtn:Button

    //update variables
    private lateinit var mTitle:String
    private lateinit var mDesc:String
    private lateinit var mDate:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        TitleEditText = findViewById(R.id.Title)
        DescEditText = findViewById(R.id.Descrption)
        DateEditText = findViewById(R.id.Date)
        okbtn = findViewById(R.id.Ok_button)

        //getting the update input
        var extras = intent.extras
        extras?.let {
            mTitle = extras.get(EXTRA_KEY_TITLE)  as String
            mDesc = extras.get(EXTRA_KEY_DESC) as String
            mDate = extras.get(EXTRA_KEY_DATE) as String

            TitleEditText.setText(mTitle)
            DescEditText.setText(mTitle)
            DateEditText.setText(mTitle)

            TitleEditText.isEnabled = false

        }

        okbtn.setOnClickListener {
            val intent = Intent()
            if (TextUtils.isEmpty(TitleEditText.text) || TextUtils.isEmpty(TitleEditText.text)){
                setResult(RESULT_ERROR, intent)
            }
            else {
                intent.putExtra(EXTRA_KEY_TITLE, TitleEditText.text.toString())
                intent.putExtra(EXTRA_KEY_DESC, DescEditText.text.toString())
                intent.putExtra(EXTRA_KEY_DATE, DateEditText.text.toString())

                setResult(RESULT_SAVE, intent)

            }
            finish()
        }



    }
}
