package com.example.isur.pam_lab_fisrtapp

class Timer(var min:Int = 0,
            var minUnit: Int = 0,
            var sec:Int = 0,
            var secUnit:Int = 0,
            var running: Boolean = false,
            var stop: Boolean = false){


    fun start(){
        running = true
        stop = false
    }

    fun pause(){
        running = false
    }

    fun stop(){
        min = 0
        minUnit = 0
        sec = 0
        secUnit = 0
        running = false
        stop = true
    }

    fun incrementMin(){
        if(min < 9) min++
    }

    fun incrementMinUnit(){
        if(minUnit < 9) minUnit++
        else if(min < 9){
            incrementMin()
            minUnit = 0
        }
    }

    fun incrementSec(){
        if(sec < 5) sec++
        else if(minUnit < 9 || min < 9 ){
            incrementMinUnit()
            sec = 0
        }
    }

    fun incrementSecUnit(){
        if(secUnit < 9) secUnit++
        else if(sec < 9 || minUnit < 9 || min < 9){
            secUnit = 0
            incrementSec()
        }
    }

    fun decrementMin(){
        if(min > 0) min--
    }

    fun decrementMinUnit(){
        if(minUnit > 0) minUnit--
        else if(min > 0) {
            decrementMin()
            minUnit = 9
        }
    }

    fun decrementSec(){
        if(sec > 0) sec--
        else if(min > 0 || minUnit > 0){
            sec = 5
            decrementMinUnit()
        }
    }

    fun decrementSecUnit(){
        if(secUnit > 0) secUnit--
        else if(sec > 0 || minUnit > 0 || min > 0){
            secUnit = 9
            decrementSec()
        }else stop = true
    }

}