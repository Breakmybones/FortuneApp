package com.example.database

import android.content.Context
import androidx.room.Room
import com.example.database.model.UserLocal

class DataBaseRepository(context: Context) {

    val dataBase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

    private val userDao by lazy {
        dataBase.getUserDao()
    }

    suspend fun addUser(user: UserLocal) {
        userDao.add(user)
    }

    suspend fun findUser(email: String?) {
        userDao.findUser(email)
    }

    suspend fun setNewEmail(newEmail: String, oldEmail: String) {
        userDao.setNewEmail(newEmail, oldEmail)
    }

    suspend fun setNewMale(male: Boolean, email: String) {
        userDao.setNewMale(male, email)
    }

    suspend fun setNewUsername(username: String, email: String) {
        userDao.setNewUsername(username, email)
    }

    suspend fun deleteUser(user: UserLocal) {
        userDao.delete(user)
    }

    suspend fun setNewBirth(birth: String, email: String) {
        userDao.setNewBirth(birth, email)
    }

    companion object {
        private const val DATABASE_NAME = "fortune.db"
    }
}