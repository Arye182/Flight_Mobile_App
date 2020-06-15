package com.example.flightmobileapp.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightmobileapp.model.ConnectionEntity
import com.example.flightmobileapp.model.ConnectionRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ConnectionViewModel (private val repository: ConnectionRepository) : ViewModel(), Observable {

    val connections = repository.connections

    @Bindable
    val inputUrl = MutableLiveData<String>()
//    val inputDate = MutableLiveData<String>()
//    val inputSuccess = MutableLiveData<Boolean>()

    @Bindable
    val connectButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllButtonText = MutableLiveData<String>()

    init {
        connectButtonText.value = "Connect"
        clearAllButtonText.value = "Clear Connections"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun connect() {
        val url : String = inputUrl.value!!
        // Current date and time
        val dateTime : String = LocalDateTime.now().toString()
        val success : Boolean = connectServer(url)
        insert (ConnectionEntity(0, url, dateTime, success))
        inputUrl.value = null
    }

    fun clearAll() {
        clearAll()
    }

    fun connectServer(url : String) : Boolean{
        return true
    }

    fun insert(connection: ConnectionEntity) = viewModelScope.launch {
            repository.insert(connection)
    }

    fun update(connection: ConnectionEntity) = viewModelScope.launch {
        repository.update(connection)
    }

    fun delete(connection: ConnectionEntity) = viewModelScope.launch {
        repository.delete(connection)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}
