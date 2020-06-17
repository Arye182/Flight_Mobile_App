package com.example.flightmobileapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.flightmobileapp.R
import com.example.flightmobileapp.databinding.ActivityMainBinding

class ControlActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_controll)
        setContentView(R.layout.activity_controll)
    }
}