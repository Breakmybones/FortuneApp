package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cookie")
data class CookieLocal(
    @PrimaryKey val description: String
)