package com.example.flightmobileapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ConnectionEntity::class], version = 1, exportSchema = false)
abstract class ConnectionDataBase : RoomDatabase(){

    abstract val connectionDAO : ConnectionDAO

    companion object {
        @Volatile
        private var INSTANCE : ConnectionDataBase? = null
        fun getInstance(context: Context) : ConnectionDataBase{
            synchronized(this){
                var instance:ConnectionDataBase? = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ConnectionDataBase::class.java,
                        "connections_data_base"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}
