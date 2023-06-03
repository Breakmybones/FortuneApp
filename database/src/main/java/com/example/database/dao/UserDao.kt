package com.example.database.dao

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

    @Query("UPDATE users SET email = :newEmail WHERE email = :oldEmail")
    suspend fun setNewEmail(newEmail: String, oldEmail: String)

    @Query("UPDATE users SET username = :username WHERE email = :email")
    suspend fun setNewUsername(username: String, email: String)

    @Query("UPDATE users SET male = :male WHERE email = :email")
    suspend fun setNewMale(male: Boolean, email: String)

    @Query("UPDATE users SET dayOfBirth = :birth WHERE email = :email")
    suspend fun setNewBirth(birth: String, email: String)

    @Query("UPDATE users SET male = :male, username = :username, dayOfBirth = :birth WHERE email = :email")
    suspend fun updateUser(male: Boolean, username: String, birth: String, email: String)
}