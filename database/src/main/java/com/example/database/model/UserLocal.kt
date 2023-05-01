package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserLocal(
        @PrimaryKey val email: String,
        val username: String?,
        val dayOfBirth: String?,
        val male: Boolean?
)