package com.example.isur.pam_lab_fisrtapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import android.media.RingtoneManager
import android.view.View


class MainActivity : AppCompatActivity() {

    private var timer = Timer(this)
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        timer.handler.removeCallbacks(timer)
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

    fun stopSoundButton(){
        buttonStopSound.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val min = savedInstanceState?.getCharSequence("min")
        val minUnit = savedInstanceState?.getCharSequence("minUnit")
        val sec = savedInstanceState?.getCharSequence("sec")
        val secUnit = savedInstanceState?.getCharSequence("secUnit")
        val stop = savedInstanceState?.getCharSequence("stop")
        val running = savedInstanceState?.getCharSequence("running")

        setContentView(R.layout.activity_main)
        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(applicationContext, notification)

        try {
            timer = Timer(this,
                    ringtone,
                    min.toString().toInt(),
                    minUnit.toString().toInt(),
                    sec.toString().toInt(),
                    secUnit.toString().toInt(),
                    running.toString().toBoolean(),
                    stop.toString().toBoolean())
            timer.viewUpdate()
            if (timer.running) {
                timer.start()
            }
        } catch (e: Exception) {
            timer = Timer(this, ringtone)
        }

        buttonAddMin.setOnClickListener {
            timer.incrementMin()
        }

        buttonAddMinUnit.setOnClickListener {
            timer.incrementMinUnit()
        }

        buttonAddSec.setOnClickListener {
            timer.incrementSec()
        }

        buttonAddSecUnit.setOnClickListener {
            timer.incrementSecUnit()
        }

        buttonSubMin.setOnClickListener {
            timer.decrementMin()
        }

        buttonSubMinUnit.setOnClickListener {
            timer.decrementMinUnit()
        }

        buttonSubSec.setOnClickListener {
            timer.decrementSec()
        }

        buttonSubSecUnit.setOnClickListener {
            timer.decrementSecUnit()
        }

        buttonStart.setOnClickListener {
            Toast.makeText(this, "Timer start", Toast.LENGTH_SHORT).show()
            timer.start()
        }

        buttonStop.setOnClickListener {
            Toast.makeText(this, "Timer stop", Toast.LENGTH_SHORT).show()
            timer.stop()
        }

        buttonPause.setOnClickListener {
            Toast.makeText(this, "Timer pause", Toast.LENGTH_LONG).show();
            timer.pause()
        }

        buttonStopSound.setOnClickListener {
            timer.stopSound()
            buttonStopSound.visibility = View.INVISIBLE
        }
    }
}
