package com.example.database

import android.content.Context
import androidx.room.Room
import com.example.database.model.*

class DataBaseRepository(context: Context) {

    val dataBase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    private val userDao by lazy {
        dataBase.getUserDao()
    }

    private val dayDao by lazy {
        dataBase.getDayDao()
    }

    suspend fun addUser(user: UserLocal) {
        userDao.add(user)
    }

    suspend fun findUser(): UserLocal? {
        return userDao.findUser()
    }

    suspend fun updateUser(male: Boolean, username: String, birth: String, email: String) {
        userDao.updateUser(male, username, birth, email)
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

    suspend fun addCard(cardLocal: CardLocal) {
        dayDao.addCard(cardLocal)
    }

    suspend fun addColor(colorLocal: ColorLocal) {
        dayDao.addColor(colorLocal)
    }

    suspend fun addDigit(digitLocal: DigitLocal) {
        dayDao.addDigit(digitLocal)
    }

    suspend fun addCookie(cookieLocal: CookieLocal) {
        dayDao.addCookie(cookieLocal)
    }

    suspend fun addYes(yesLocal: YesLocal) {
        dayDao.addYes(yesLocal)
    }

    suspend fun deleteCard(cardLocal: CardLocal) {
        dayDao.deleteCard(cardLocal)
    }

    suspend fun deleteColor(colorLocal: ColorLocal) {
        dayDao.deleteColor(colorLocal)
    }

    suspend fun deleteDigit(digitLocal: DigitLocal) {
        dayDao.deleteDigit(digitLocal)
    }

    suspend fun deleteYes(yesLocal: YesLocal) {
        dayDao.deleteYes(yesLocal)
    }

    suspend fun deleteCookie(cookieLocal: CookieLocal) {
        dayDao.deleteCookie(cookieLocal)
    }

    suspend fun findCard() {
        dayDao.findCard()
    }

    suspend fun findColor() {
        dayDao.findColor()
    }

    suspend fun findDigit() {
        dayDao.findDigit()
    }

    suspend fun findYes() {
        dayDao.findYes()
    }

    suspend fun findCookie() {
        dayDao.findCookie()
    }

    companion object {
        private const val DATABASE_NAME = "fortune.db"
    }
}