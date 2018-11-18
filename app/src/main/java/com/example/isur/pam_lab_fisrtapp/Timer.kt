package com.example.isur.pam_lab_fisrtapp

import android.media.Ringtone
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*


class Timer(var view: MainActivity,
            var ringtone: Ringtone? = null,
            var min:Int = 0,
            var minUnit: Int = 0,
            var sec:Int = 0,
            var secUnit:Int = 0,
            var running: Boolean = false,
            var stop: Boolean = false) : Runnable{

    private val handler = Handler()

    override fun run(){
        if(!stop && running){
            decrementSecUnit()
            handler.postDelayed(this, 1000)
        }
    }

    fun viewUpdate(){
        view.textMin.text = min.toString()
        view.textMinUnit.text = minUnit.toString()
        view.textSec.text = sec.toString()
        view.textSecUnit.text = secUnit.toString()
    }

    fun stopSound(){
        ringtone?.stop()
    }

    fun start(){
        running = true
        stop = false
        view.buttonsEnabled(running)
        run()
    }

    fun pause(){
        running = false
        view.buttonsEnabled(running)
    }

    fun stop(){
        min = 0
        minUnit = 0
        sec = 0
        secUnit = 0
        running = false
        stop = true
        stopSound()
        view.buttonsEnabled(running)
        viewUpdate()
    }

    private fun endOfTimer(){
        stop()
        ringtone?.play()
        view.stopSoundButton()
    }

    fun incrementMin(){
        if(min < 9) min++
        viewUpdate()
    }

    fun incrementMinUnit(){
        if(minUnit < 9) minUnit++
        else if(min < 9){
            incrementMin()
            minUnit = 0
        }

        viewUpdate()
    }

    fun incrementSec(){
        if(sec < 5) sec++
        else if(minUnit < 9 || min < 9 ){
            incrementMinUnit()
            sec = 0
        }
        viewUpdate()
    }

    fun incrementSecUnit(){
        if(secUnit < 9) secUnit++
        else if(sec < 9 || minUnit < 9 || min < 9){
            secUnit = 0
            incrementSec()
        }
        viewUpdate()
    }

    fun decrementMin(){
        if(min > 0) min--
        viewUpdate()
    }

    fun decrementMinUnit(){
        if(minUnit > 0) minUnit--
        else if(min > 0) {
            decrementMin()
            minUnit = 9
        }
        viewUpdate()
    }

    fun decrementSec(){
        if(sec > 0) sec--
        else if(min > 0 || minUnit > 0){
            sec = 5
            decrementMinUnit()
        }
        viewUpdate()
    }

    fun decrementSecUnit(){
        if(secUnit > 0) secUnit--
        else if(sec > 0 || minUnit > 0 || min > 0){
            secUnit = 9
            decrementSec()
        }else if(running){
           endOfTimer()
        }
        viewUpdate()
    }
}