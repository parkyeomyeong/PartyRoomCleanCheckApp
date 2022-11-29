package com.kmc.cleaningcheck.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRoomDao(): RoomDao

    companion object {
        val databaseName = "db_checkList"
        var appDatabase: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
            }
            return appDatabase
        }
    }
}