package com.example.database.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.database.utils.UriTypeConverter

@Entity(tableName = "users")
@TypeConverters(UriTypeConverter::class)
data class UserLocal(
        @PrimaryKey val email: String,
        val username: String?,
        val dayOfBirth: String?,
        val male: Boolean?,
        val sign: String?,
        val icon: Uri?
)