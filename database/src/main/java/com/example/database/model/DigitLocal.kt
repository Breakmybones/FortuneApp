package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "digit")
data class DigitLocal(
    @PrimaryKey val digit: Int,
    val description: String?
)