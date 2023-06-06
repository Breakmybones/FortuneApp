package com.example.feature_signs_impl.di

import com.example.feature_signs_api.model.ListResponse
import com.example.feature_signs_api.model.ZodiacInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface ZodiacAPI {

    @GET("/zodiac/{item}")
    suspend fun getAllData(
        @Path("item") item: String
    ): List<ZodiacModel>
}