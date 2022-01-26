package com.example.workout7

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.back_dialogue.*
import java.util.*

class ExerciseActivity : AppCompatActivity() , TextToSpeech.OnInitListener {
    var tts : TextToSpeech ? = null
    var restTimer : CountDownTimer? = null
    var restProgress = 0
    var exTurn = true
    val exerciseListData = exerciseData.getExerciseData()
    var exId = 0
    var mediaP : MediaPlayer? = null
    var adaptor : ExerciseStatusBar? = null
    var dialog : Dialog? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbarExercise)
        dialog = Dialog(this)
        dialog!!.setContentView(R.layout.back_dialogue)
        dialog!!.quit.setOnClickListener {
            finish()
            dialog!!.dismiss()
        }
        dialog!!.cancel.setOnClickListener {
            dialog!!.dismiss()
        }



        upText.text = "Upcoming Exercise"
        exName.text = exerciseListData!!.get(exId).name
        setupRestTimer(1000)

        val supportAction = supportActionBar

        if(supportAction != null)
            supportAction.setDisplayHomeAsUpEnabled(true)


        toolbarExercise.setNavigationOnClickListener{
            dialog!!.show()

        }
        tts = TextToSpeech(this,this)
        setupRecyclerView(exerciseListData)
    }

    override fun onBackPressed() {

        dialog!!.show()
    }

    override fun onDestroy() {
        if(mediaP != null)
            mediaP!!.stop()

        if(tts != null)
        {
            tts!!.stop()
            tts!!.shutdown()
        }

        if(restTimer != null){
            restProgress = 0
            restTimer!!.cancel()
        }
        super.onDestroy()
    }
    fun setupRestTimer(totalTime: Long){
        if(restTimer != null){
            restProgress = 0
            restTimer!!.cancel()
        }
        if(exTurn == true)
        {
            mediaP = MediaPlayer.create(applicationContext,R.raw.startsound)
            mediaP!!.isLooping = false
            mediaP!!.start()
        }
        setupRestProgressbar(totalTime)
    }

    fun setupRestProgressbar(totalTime : Long)
    {
        restTimer = object : CountDownTimer(totalTime,10000){

            override fun onTick(p0: Long) {
                restProgress++
                progressBar.progress = progressBar.max-restProgress

                timeText.text = (progressBar.max-restProgress).toString()
            }

            override fun onFinish() {
                if(exTurn and (exId != exerciseListData!!.size)){
                    exTurn = false
                    titleText.text = exerciseListData.get(exId).name.toString()
                    exImage.setImageResource(exerciseListData.get(exId).pic)
                    exerciseListData.get(exId).isSelected = true
                    adaptor!!.notifyDataSetChanged()
                    exId++
                    progressBar.max = 15
                    speakText(titleText.text.toString())
                    setupRestTimer(1500)
                    upText.text = ""
                    exName.text = ""
                }
                else if(exId != exerciseListData!!.size){
                    exTurn = true
                    titleText.text = "Get Ready"
                    exImage.setImageBitmap(null)
                    progressBar.max = 10
                    exerciseListData.get(exId-1).isCompleted = true
                    exerciseListData.get(exId-1).isSelected = false
                    adaptor!!.notifyDataSetChanged()
                    setupRestTimer(1000)
                    upText.text = "Upcoming Exercise"
                    exName.text = exerciseListData.get(exId).name
                }
                else if(exId == exerciseListData!!.size)
                {
                    exerciseListData.get(exId-1).isCompleted = true
                    exerciseListData.get(exId-1).isSelected = false
                    adaptor!!.notifyDataSetChanged()
                    val intent : Intent = Intent(this@ExerciseActivity,Finish::class.java)
                    startActivity(intent)
                }

            }
        }.start()
    }

    override fun onInit(p0: Int) {
        if(p0 == TextToSpeech.SUCCESS)
        {
            var res = tts!!.setLanguage(Locale.US)

            if(res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_MISSING_DATA)
                Toast.makeText(this,"Language not supported",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"TextToSpeech not installed",Toast.LENGTH_LONG).show()
        }
    }

    fun speakText(text : String)
    {
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")

    }
    fun  setupRecyclerView(listData : ArrayList<ExerciseModel>)
    {
        RecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        adaptor = ExerciseStatusBar(listData,this)
        RecyclerView.adapter = adaptor
    }
}


