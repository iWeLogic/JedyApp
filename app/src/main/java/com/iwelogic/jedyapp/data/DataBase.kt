package com.iwelogic.jedyapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iwelogic.jedyapp.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract fun itemDao(): MovieDao
}