package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.UserDao
import com.example.database.model.UserLocal

@Database(entities = [UserLocal::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao
}