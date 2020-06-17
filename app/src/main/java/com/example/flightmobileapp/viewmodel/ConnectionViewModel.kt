package com.example.flightmobileapp.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightmobileapp.Event
import com.example.flightmobileapp.model.ConnectionEntity
import com.example.flightmobileapp.model.ConnectionRepository
import kotlinx.coroutines.*


class ConnectionViewModel (private val repository: ConnectionRepository) : ViewModel(), Observable {

    val connections = repository.connections
    private var isDelete = false
    private lateinit var connectionToDelete : ConnectionEntity

    @Bindable
    val inputUrl = MutableLiveData<String>()

    @Bindable
    val connectButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllButtonText = MutableLiveData<String>()

    // this is the status that always changes
    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
        get() = statusMessage

    // this is another event that fired when connection success and need to move to the next activity
    private val shouldStartActivity = MutableLiveData<Event<Boolean>>()
    val shouldStartActivityFlag : LiveData<Event<Boolean>>
        get() = shouldStartActivity

    init {
        connectButtonText.value = "Connect"
        clearAllButtonText.value = "Clear All"
    }

    // insert new connection to DataBase
    private fun insert(connection: ConnectionEntity) = viewModelScope.launch {
        val newRowId: Long = repository.insert(connection)
        if (newRowId > -1) {
            statusMessage.value = Event("Connection Inserted Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    private fun delete(connection: ConnectionEntity) = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(connection)
        if (noOfRowsDeleted >0) {
        inputUrl.value = null
        isDelete = false
        clearAllButtonText.value = "Clear All"
        statusMessage.value = Event("$noOfRowsDeleted Connection Has Been Removed From DataBase")
        } else {
            statusMessage.value = Event("Error Deleting!")
        }
    }

    private fun deleteAll() = viewModelScope.launch {
        val noOfRowsDeleted : Int = repository.deleteAll()
        if (noOfRowsDeleted>0) {
            statusMessage.value = Event("$noOfRowsDeleted Connections Have Been Removed From DataBase")
        } else {
            statusMessage.value = Event("Error Deleting All Connections!")
        }
    }

    fun initDelete (connection: ConnectionEntity) {
        inputUrl.value = connection.url
        isDelete = true
        connectionToDelete = connection
        clearAllButtonText.value = "Delete"
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


     fun connect() {
            if (inputUrl.value==null){
                    statusMessage.value = Event("Please Enter URL To Connect!")
            } else {
                val url: String = inputUrl.value!!
                val success: Boolean = connectServer(url)
                insert(ConnectionEntity(id = 0, url = url, success = success))
                inputUrl.value = null
                clearAllButtonText.value = "Clear All"
                shouldStartActivity.value = Event(true)

                // TODO - add method that moves the user to the next screen - if success is true!
            }
    }

    fun clearAllOrDelete() {
        if (isDelete) {
            delete(connectionToDelete)
        } else {
            deleteAll()
        }
    }

    fun connectServer(url : String) : Boolean{
        return true
    }

}
