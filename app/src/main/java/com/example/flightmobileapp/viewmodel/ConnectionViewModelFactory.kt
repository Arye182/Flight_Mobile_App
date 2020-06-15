package com.example.flightmobileapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flightmobileapp.model.ConnectionRepository

class ConnectionViewModelFactory (private val repository : ConnectionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConnectionViewModel::class.java)){
            return ConnectionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }


}
