package com.example.flightmobileapp.model

class ConnectionRepository (private val dao : ConnectionDAO){

    val connections = dao.getAllConnections()

    suspend fun insert (connection: ConnectionEntity) : Long{
        return dao.insertConnection(connection)
    }

    suspend fun delete (connection: ConnectionEntity) : Int{
       return dao.deleteConnection(connection)
    }

    suspend fun deleteAll() : Int {
        return dao.deleteAllConnections()
    }
}
