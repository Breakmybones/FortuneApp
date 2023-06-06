package com.example.feature_signs_impl.data

import android.util.Log
import com.example.feature_signs_api.interfaces.ZodiacRepository
import com.example.feature_signs_api.model.ListResponse
import com.example.feature_signs_api.model.ZodiacInfo
import com.example.feature_signs_impl.di.ZodiacAPI
import com.example.feature_signs_impl.di.ZodiacModel

@Suppress("UNUSED_CHANGED_VALUE")
class ZodiacRepositoryImpl(private val api: ZodiacAPI) : ZodiacRepository {

    override suspend fun getCompatibility(zodiac: String): ListResponse {
        val value = ArrayList<ZodiacInfo>()
        val response = api.getAllData(zodiac)
        Log.e("Repository", response.toString())
        response.forEach {
            var id = 1
            value.add(
                ZodiacInfo(
                    id = id,
                    name = it.name,
                    love = it.love,
                    friendship = it.friendship
                )
            )
            id++
        }
        return ListResponse(value)
    }
}