package com.example.workout7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_exercise.toolbarExercise
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(toolbarExercise)
        val supportAction = supportActionBar

        if(supportAction != null)
            supportAction.setDisplayHomeAsUpEnabled(true)

        toolbarExercise.setNavigationOnClickListener{
            onBackPressed()

        }
        setUpRecyclerView()


    }

    fun getDateList() : ArrayList<String>{
        val db = SQLHelper(this,null)
        val list = db.getAllDates()

        return list
    }

    fun setUpRecyclerView(){
        val list = getDateList()
        for(i in list)
            Log.i("LIST",""+i)

        val adaptor = DateRecyclerView(list,this)
        recyclerDate.adapter = adaptor
        recyclerDate.layoutManager = LinearLayoutManager(this)


    }
}