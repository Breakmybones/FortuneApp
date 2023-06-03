package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.DayDao
import com.example.database.dao.UserDao
import com.example.database.model.*

@Database(entities = [
    UserLocal::class,
    CardLocal::class,
    ColorLocal::class,
    DigitLocal::class,
    YesLocal::class,
    CookieLocal::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getDayDao(): DayDao
}