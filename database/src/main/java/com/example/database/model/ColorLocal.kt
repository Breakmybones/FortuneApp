package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "color")
data class ColorLocal (
    @PrimaryKey val name: String,
    val description: String?
)