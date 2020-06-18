package com.example.flightmobileapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.flightmobileapp.R
import com.example.flightmobileapp.databinding.ActivityControllBinding
import com.example.flightmobileapp.viewmodel.ControlViewModel
import com.example.flightmobileapp.viewmodel.ControlViewModelFactory

class ControlActivity : AppCompatActivity() {

    private lateinit var binding : ActivityControllBinding
    private lateinit var controlViewModel : ControlViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_controll
        )
        val factory = ControlViewModelFactory()
        controlViewModel = ViewModelProvider(this, factory).get(ControlViewModel::class.java)
        binding.controlViewModel = controlViewModel
        binding.lifecycleOwner = this
    }
}