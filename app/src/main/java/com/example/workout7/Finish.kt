package com.example.workout7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_exercise.toolbarExercise
import kotlinx.android.synthetic.main.activity_finish.*
import java.text.SimpleDateFormat
import java.util.*

class Finish : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        setSupportActionBar(toolbarExercise)

        val supportAction = supportActionBar

        if(supportAction != null)
            supportAction.setDisplayHomeAsUpEnabled(true)

        toolbarExercise.setNavigationOnClickListener{
            onBackPressed()
        }

        editDatabase()

        finish.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)

            startActivity(intent)

            finish()
        }


    }
        fun editDatabase()
        {
            val calendar = Calendar.getInstance()
            val date = calendar.time
            Toast.makeText(this,"Working",Toast.LENGTH_LONG).show()
            Log.i("Date" , ""+date)
            val sdfDate = SimpleDateFormat("dd MMM yyyy HH:mm:ss",Locale.getDefault())

            val FinalDate = sdfDate.format(date)
            val db = SQLHelper(this,null)
            db.addData(FinalDate)

        }

}