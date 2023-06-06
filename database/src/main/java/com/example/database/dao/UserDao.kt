package com.example.database.dao

import android.net.Uri
import androidx.room.*
import com.example.database.model.UserLocal

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(user: UserLocal)

    @Delete
    suspend fun delete(user: UserLocal)

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun findUser(): UserLocal?

    @Query("UPDATE users SET male = :male, username = :username, dayOfBirth = :birth, sign = :sign, icon = :icon WHERE email = :email")
    suspend fun updateUser(male: Boolean, username: String, birth: String, email: String, sign: String, icon: Uri)
}