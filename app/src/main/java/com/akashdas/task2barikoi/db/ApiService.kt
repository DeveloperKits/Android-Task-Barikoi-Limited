package com.akashdas.task2barikoi.db

import com.akashdas.task2barikoi.Model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("v2/api/search/nearby/category/bkoi_e0a87a6dea3f2ec5244147a6db2aef70687636e4ca331381ae378e0d44268fbf/1/10")
    suspend fun getNearbyPlaces(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double,
        @Query("ptype") ptype: String,
    ): ResponseModel
}