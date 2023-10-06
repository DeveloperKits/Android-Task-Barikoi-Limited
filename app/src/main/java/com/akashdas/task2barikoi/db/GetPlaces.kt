package com.akashdas.task2barikoi.db

import com.akashdas.task2barikoi.Model.ResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class GetPlaces {
    private val apiService = ApiClient.instance.create(ApiService::class.java)

    suspend fun getNearbyPlaces(
        longitude: Double,
        latitude: Double,
        ptype: String
    ): ResponseModel {
        return withContext(Dispatchers.IO) {
            apiService.getNearbyPlaces(longitude, latitude, ptype)
        }
    }
}