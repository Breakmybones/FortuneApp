package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.dao.DayDao
import com.example.database.dao.UserDao
import com.example.database.model.*
import com.example.database.utils.UriTypeConverter

@Database(entities = [
    UserLocal::class,
    CardLocal::class,
    ColorLocal::class,
    DigitLocal::class,
    YesLocal::class,
    CookieLocal::class], version = 3)
@TypeConverters(UriTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getDayDao(): DayDao
}