package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "yes")
data class YesLocal(
    @PrimaryKey val description: String
)