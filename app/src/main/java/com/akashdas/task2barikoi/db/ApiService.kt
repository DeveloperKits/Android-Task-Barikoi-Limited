package com.akashdas.task2barikoi.db

import com.akashdas.task2barikoi.Model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v2/api/search/nearby/category/{api_key}")
    suspend fun getNearbyPlaces(
        @Path("api_key") apiKey: String,
        @Query("longitude") longitude: String,
        @Query("latitude") latitude: String,
        @Query("ptype") ptype: String,
        @Query("distance") distance: Double,
        @Query("limit") limit: Int
    ): Response<ResponseModel>
}