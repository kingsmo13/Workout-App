package com.example.workout7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_exercise.toolbarExercise

class BMI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        setSupportActionBar(toolbarExercise)
        val supportAction = supportActionBar

        if(supportAction != null)
            supportAction.setDisplayHomeAsUpEnabled(true)


        toolbarExercise.setNavigationOnClickListener{
            onBackPressed()

        }
        calcBTN.setOnClickListener {
            if(rb1.isChecked) {
                if (validatedMetric())
                    calculateMetricBMI()
                else Toast.makeText(this, "Please Fill All the Feilds", Toast.LENGTH_LONG).show()
            }
            else {
                if (validatedUS())
                    calculateUSBMI()
                else Toast.makeText(this, "Please Fill All the Feilds", Toast.LENGTH_LONG).show()
            }
        }
        rb1.setOnClickListener {
            usHeight.visibility = View.GONE
            metricHeight.visibility = View.VISIBLE
        }
        rb2.setOnClickListener {
            usHeight.visibility = View.VISIBLE
            metricHeight.visibility = View.GONE
        }
    }
    fun calculateMetricBMI()
    {
        val ht = height.text.toString().toDouble()
        val wt = weight.text.toString().toDouble()
        var bmi : Double = (wt/(ht*ht))*10000.0
        bmi = Math.round(bmi*10.0).toDouble()/10.0;
        var str = "BMI : ${bmi}\n"
        if(bmi > 24.9)
            str += "You are Over-Weight"
        else if(bmi > 18.0)
            str += "You are Healthy"
        else str += "You are Under-Weight"
        textBMI.text = str
        
    }
    fun calculateUSBMI()
    {
        val ht1 = heightFeet.text.toString().toDouble()*12
        val ht2 = (heightInches.text.toString().toDouble()+ht1)*2.54
        val wt = weight.text.toString().toDouble()
        var bmi : Double = (wt/(ht2*ht2))*10000.0
        bmi = Math.round(bmi*10.0).toDouble()/10.0;
        var str = "BMI : ${bmi}\n"
        if(bmi > 24.9)
            str += "You are Over-Weight"
        else if(bmi > 18.0)
            str += "You are Healthy"
        else str += "You are Under-Weight"
        textBMI.text = str
    }

    fun validatedMetric() : Boolean
    {
        var flag : Boolean = true
        if(height.text.toString().isEmpty())
            flag = false
        if(weight.text.toString().isEmpty())
            flag = false

        return flag
    }

    fun validatedUS() : Boolean{
        var flag : Boolean = true
        if(heightFeet.text.toString().isEmpty())
            flag = false
        if(heightInches.text.toString().isEmpty())
            flag = false
        if(weight.text.toString().isEmpty())
            flag = false

        return flag


    }
}