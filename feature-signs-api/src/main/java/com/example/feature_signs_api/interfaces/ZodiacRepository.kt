package com.example.feature_signs_api.interfaces

import com.example.feature_signs_api.model.ListResponse

interface ZodiacRepository {
    suspend fun getCompatibility(zodiac: String): ListResponse
}