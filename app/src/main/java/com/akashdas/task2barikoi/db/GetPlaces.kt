package com.akashdas.task2barikoi.db

import com.akashdas.task2barikoi.Model.ResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun getNearbyPlaces(
    apiKey: String,
    longitude: String,
    latitude: String,
    ptype: String,
    distance: Double,
    limit: Int
): Response<ResponseModel> {
    return withContext(Dispatchers.IO) {
        val apiService = ApiClient.getClient().create(ApiService::class.java)
        apiService.getNearbyPlaces(apiKey, longitude, latitude, ptype, distance, limit)
    }
}
