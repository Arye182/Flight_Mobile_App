package com.example.flightmobileapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flightmobileapp.R
import com.example.flightmobileapp.convertLongToDateString
import com.example.flightmobileapp.databinding.ConnectionListItemBinding
import com.example.flightmobileapp.generated.callback.OnClickListener
import com.example.flightmobileapp.model.ConnectionEntity
import kotlinx.android.synthetic.main.connection_list_item.view.*

class ConnectionsRecyclerViewAdapter ( private val clickListener: (ConnectionEntity)->Unit)
    : RecyclerView.Adapter<MyViewHolder>(){


    private val connectionsList =  ArrayList<ConnectionEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        val binding : ConnectionListItemBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.connection_list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return connectionsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(connectionsList[position], clickListener)
    }

    fun setList(connections : List<ConnectionEntity>){
        connectionsList.clear()
        connectionsList.addAll(connections)
    }


}



class MyViewHolder(val binding: ConnectionListItemBinding): RecyclerView.ViewHolder(binding.root){
    fun bind (connection: ConnectionEntity, clickListener: (ConnectionEntity)->Unit) {
        binding.urlTextView.text = connection.url
        binding.dateTextView.text = convertLongToDateString(connection.date)
        binding.listItemLayout.setOnClickListener{
            clickListener(connection)
        }
    }
}