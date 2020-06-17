package com.example.flightmobileapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "connections_data_table")
data class ConnectionEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "connection_id")
    val id: Int,

    @ColumnInfo(name = "connection_url")
    val url: String,

    @ColumnInfo(name = "connection_date")
    val date: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "connection_success_or_fail")
    val success: Boolean

)
