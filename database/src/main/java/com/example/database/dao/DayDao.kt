package com.example.database.dao

import androidx.room.*
import com.example.database.model.*

@Dao
interface DayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addColor(color: ColorLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCard(cardLocal: CardLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDigit(digitLocal: DigitLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCookie(cookieLocal: CookieLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addYes(color: YesLocal)

    @Delete
    suspend fun deleteCard(cardLocal: CardLocal)

    @Delete
    suspend fun deleteColor(color: ColorLocal)

    @Delete
    suspend fun deleteDigit(digitLocal: DigitLocal)

    @Delete
    suspend fun deleteCookie(cookieLocal: CookieLocal)

    @Delete
    suspend fun deleteYes(yesLocal: YesLocal)

    @Query("SELECT * FROM card")
    suspend fun findCard(): CardLocal?

    @Query("SELECT * FROM color")
    suspend fun findColor(): ColorLocal?

    @Query("SELECT * FROM digit")
    suspend fun findDigit(): DigitLocal?

    @Query("SELECT * FROM yes")
    suspend fun findYes(): YesLocal?

    @Query("SELECT * FROM cookie")
    suspend fun findCookie(): CookieLocal?
}