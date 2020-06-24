package com.example.flightmobileapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flightmobileapp.R
import com.example.flightmobileapp.model.ConnectionDAO
import com.example.flightmobileapp.model.ConnectionDataBase
import com.example.flightmobileapp.model.ConnectionRepository
import com.example.flightmobileapp.viewmodel.ConnectionViewModel
import com.example.flightmobileapp.viewmodel.ConnectionViewModelFactory
import com.example.flightmobileapp.databinding.ActivityMainBinding
import com.example.flightmobileapp.model.ConnectionEntity

class LoginActivity : AppCompatActivity() {

    private lateinit var connectionViewModel : ConnectionViewModel
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: ConnectionsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        val dao:ConnectionDAO = ConnectionDataBase.getInstance(application).connectionDAO
        val repository = ConnectionRepository(dao)
        val factory = ConnectionViewModelFactory(repository)
        connectionViewModel = ViewModelProvider(this, factory).get(ConnectionViewModel::class.java)
        binding.myViewModel = connectionViewModel
        binding.lifecycleOwner = this
        initRecyclerView()

        connectionViewModel.shouldStartActivityFlag.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                if (it) {
                    val intent = Intent(this, ControlActivity::class.java).apply {  }
                    startActivity(intent)
                }
            }
        })

        connectionViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initRecyclerView(){
        binding.connectionsRecycleView.layoutManager = LinearLayoutManager(this)
        adapter = ConnectionsRecyclerViewAdapter({selectedItem: ConnectionEntity->listItemClicked(selectedItem)})
        binding.connectionsRecycleView.adapter = adapter
        displayConnectionsList()
    }

    private fun displayConnectionsList(){
        connectionViewModel.connections.observe(this, Observer {
            Log.i("MYTAG", it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()

    })
    }

    private fun listItemClicked(connection: ConnectionEntity){
        //connectionViewModel.inputUrl.value = connection.url
        connectionViewModel.initDelete(connection)
    }
}
