package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class CardLocal(
    @PrimaryKey val dignity: String,
    val name: String?,
    val description: String?

)