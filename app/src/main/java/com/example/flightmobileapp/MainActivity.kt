package com.example.flightmobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flightmobileapp.model.ConnectionDAO
import com.example.flightmobileapp.model.ConnectionDataBase
import com.example.flightmobileapp.model.ConnectionRepository
import com.example.flightmobileapp.viewmodel.ConnectionViewModel
import com.example.flightmobileapp.viewmodel.ConnectionViewModelFactory
import com.example.flightmobileapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var connectionViewModel : ConnectionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao:ConnectionDAO = ConnectionDataBase.getInstance(application).connectionDAO
        val repository = ConnectionRepository(dao)
        val factory = ConnectionViewModelFactory(repository)
        connectionViewModel = ViewModelProvider(this, factory).get(ConnectionViewModel::class.java)
        binding.myViewModel = connectionViewModel
        binding.lifecycleOwner = this
        displayConnectionsList()


    }

    private fun displayConnectionsList(){
        connectionViewModel.connections.observe(this, Observer {
            Log.i("MYTAG", it.toString())
        })
    }



}
