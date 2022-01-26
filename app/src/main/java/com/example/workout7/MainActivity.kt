package com.example.workout7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llStart.setOnClickListener {
        val intent  = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }
        BMI_btn.setOnClickListener {
            val intent = Intent(this,BMI::class.java)
            startActivity(intent)
        }
        history.setOnClickListener{
            val intent3 = Intent(this,HistoryActivity::class.java)
            startActivity(intent3)
        }
    }
}