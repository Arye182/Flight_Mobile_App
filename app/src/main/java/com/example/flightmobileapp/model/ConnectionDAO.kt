package com.example.flightmobileapp.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ConnectionDAO {

    @Insert
    suspend fun insertConnection(connection: ConnectionEntity) : Long

    @Update
    suspend fun updateConnection(connection: ConnectionEntity)

    @Delete
    suspend fun deleteConnection(connection: ConnectionEntity)

    @Query("DELETE FROM  connections_data_table")
    suspend fun deleteAllConnections()

    @Query("SELECT * FROM connections_data_table")
    fun getAllConnections(): LiveData<List<ConnectionEntity>>




}
