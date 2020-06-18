package com.example.flightmobileapp.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ControlViewModel : ViewModel(), Observable {

    @Bindable
    val aileronValueText = MutableLiveData<Double>()

    @Bindable
    val rudderValueText = MutableLiveData<Double>()

    @Bindable
    val elevatorValueText = MutableLiveData<Double>()

    @Bindable
    val throttleValueText = MutableLiveData<Double>()


    init {
        aileronValueText.value = 0.0
        throttleValueText.value = 0.0
        elevatorValueText.value = 0.0
        rudderValueText.value = 0.0
    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }




}