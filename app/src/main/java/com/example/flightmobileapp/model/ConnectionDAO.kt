package com.example.flightmobileapp.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.flightmobileapp.model.*

@Dao
interface ConnectionDAO {

    @Insert
    suspend fun insertConnection(connection: ConnectionEntity) : Long

    @Delete
    suspend fun deleteConnection(connection: ConnectionEntity) : Int

    @Query("DELETE FROM  connections_data_table")
    suspend fun deleteAllConnections() : Int

    @Query("SELECT * FROM connections_data_table  GROUP BY connection_url HAVING MAX(connection_date) ORDER BY connection_date DESC LIMIT 5")
    fun getAllConnections(): LiveData<List<ConnectionEntity>>
}
