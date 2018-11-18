package com.example.isur.pam_lab_fisrtapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import android.media.RingtoneManager
import android.media.Ringtone
import android.view.View


class MainActivity : AppCompatActivity() {
var timer = Timer()
    fun saveText(timer: Timer) {
        textMin.text = timer.min.toString()
        textMinUnit.text = timer.minUnit.toString()
        textSec.text = timer.sec.toString()
        textSecUnit.text = timer.secUnit.toString()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        val min = timer.min.toString()
        val minUnit = timer.minUnit.toString()
        val sec = timer.sec.toString()
        val secUnit = timer.secUnit.toString()
        val running = timer.running.toString()
        val stop = timer.stop.toString()

        outState?.putCharSequence("min", min)
        outState?.putCharSequence("minUnit", minUnit)
        outState?.putCharSequence("sec", sec)
        outState?.putCharSequence("secUnit", secUnit)
        outState?.putCharSequence("running", running)
        outState?.putCharSequence("stop", stop)



    }



    fun buttonsEnabled(running: Boolean){
        buttonStart.isEnabled = !running
        buttonAddMin.isEnabled = !running
        buttonSubMin.isEnabled = !running
        buttonAddMinUnit.isEnabled = !running
        buttonSubMinUnit.isEnabled = !running
        buttonAddSec.isEnabled = !running
        buttonSubSec.isEnabled = !running
        buttonAddSecUnit.isEnabled = !running
        buttonSubSecUnit.isEnabled = !running
    }


    fun timerGo(timer:Timer){
        timer.start()
        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val r = RingtoneManager.getRingtone(applicationContext, notification)

        buttonsEnabled(timer.running)
        val handler = Handler()

        val runnable = object : Runnable {
            override fun run() {

                if (!timer.stop && timer.running) {
                    timer.decrementSecUnit()
                    saveText(timer)
                    handler.postDelayed(this, 1000)
                } else {
                    r.play()
                    timer.running = false;
                    buttonsEnabled(timer.running)
                    buttonStopSound.visibility = View.VISIBLE
                    buttonStopSound.setOnClickListener {
                        r.stop()
                        buttonStopSound.visibility = View.INVISIBLE
                    }
                    r.
                }
            }

        }

        handler.postDelayed(runnable, 1000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var min = savedInstanceState?.getCharSequence("min")
        var minUnit = savedInstanceState?.getCharSequence("minUnit")
        var sec = savedInstanceState?.getCharSequence("sec")
        var secUnit = savedInstanceState?.getCharSequence("secUnit")
        var stop = savedInstanceState?.getCharSequence("stop")
        var running = savedInstanceState?.getCharSequence("running")
        setContentView(R.layout.activity_main)
        try{
            timer = Timer(min.toString().toInt(), minUnit.toString().toInt(), sec.toString().toInt(), secUnit.toString().toInt(), running.toString().toBoolean(), stop.toString().toBoolean())
            if(timer.running){
                timerGo(timer)
            }
        } catch(e: Exception) {
            timer = Timer()
        }


        saveText(timer)


        buttonAddMin.setOnClickListener {
            if (!timer.running) {
                timer.incrementMin()
                saveText(timer)
            }
        }

        buttonAddMinUnit.setOnClickListener {
            if (!timer.running) {
                timer.incrementMinUnit()
                saveText(timer)
            }
        }

        buttonAddSec.setOnClickListener {
            if (!timer.running) {
                timer.incrementSec()
                saveText(timer)
            }
        }

        buttonAddSecUnit.setOnClickListener {
            if (!timer.running) {
                timer.incrementSecUnit()
                saveText(timer)
            }
        }

        buttonSubMin.setOnClickListener {
            if (!timer.running) {
                timer.decrementMin()
                saveText(timer)
            }
        }

        buttonSubMinUnit.setOnClickListener {
            if (!timer.running) {
                timer.decrementMinUnit()
                saveText(timer)
            }
        }

        buttonSubSec.setOnClickListener {
            if (!timer.running) {
                timer.decrementSec()
                saveText(timer)
            }
        }

        buttonSubSecUnit.setOnClickListener {
            if (!timer.running) {
                timer.decrementSecUnit()
                saveText(timer)
            }
        }





        buttonStart.setOnClickListener {
            Toast.makeText(this, "Timer start", Toast.LENGTH_SHORT).show()

           timerGo(timer)
        }

        buttonStop.setOnClickListener {
            Toast.makeText(this, "Timer stop", Toast.LENGTH_SHORT).show()
            timer.stop()
            saveText(timer)
        }

        buttonPause.setOnClickListener {
            Toast.makeText(this, "Timer pause", Toast.LENGTH_LONG).show();
            timer.pause()
        }
    }
}
