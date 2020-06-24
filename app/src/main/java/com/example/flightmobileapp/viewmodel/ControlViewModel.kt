package com.example.flightmobileapp.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.flightmobileapp.Event

class ControlViewModel : ViewModel(), Observable {

    val throttle = MutableLiveData("0.0")
    val rudder = MutableLiveData("0.0")
    val aileron = MutableLiveData("0.0")
    val elevator = MutableLiveData("0.0")

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    fun updateThrottle(percentage: Int) {
        throttle.value = percentage.toFloat().div(100).toString()
    }
    fun updateRudder(percentage: Int) {
        rudder.value = percentage.toFloat().div(100).toString()
    }

    fun updateElevatorAileron(angle: Int, strength: Int) {

        val angle_rad = Math.toRadians(angle.toDouble())
        val base_radius : Double = ((250*80)/200).toDouble()
        val joy_radius : Double = (strength*base_radius)/100
        //calculated
        val y : Double = joy_radius * kotlin.math.sin(angle_rad)
        val x : Double = joy_radius * kotlin.math.cos(angle_rad)
        //normalize the value between 0 to 1
        val normalize_y : Double =  y / base_radius
        val normalize_x : Double =  x / base_radius
        aileron.value = "%.2f".format(normalize_x)
        elevator.value = "%.2f".format(normalize_y)
    }

}