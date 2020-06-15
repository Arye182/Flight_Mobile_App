package com.example.flightmobileapp.model

class ConnectionRepository (private val dao : ConnectionDAO){

    val connections = dao.getAllConnections()

    suspend fun insert (connection: ConnectionEntity){
        dao.insertConnection(connection);
    }

    suspend fun update (connection: ConnectionEntity){
        dao.updateConnection(connection)
    }

    suspend fun delete (connection: ConnectionEntity){
        dao.deleteConnection(connection)
    }

    suspend fun deleteAll() {
        dao.deleteAllConnections()
    }
}
