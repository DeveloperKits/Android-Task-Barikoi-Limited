package com.akashdas.task2barikoi.Model

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("distance_in_meters") val distanceInMeters: Double,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("Address") val address: String,
    @SerializedName("city") val city: String,
    @SerializedName("area") val area: String,
    @SerializedName("pType") val pType: String,
    @SerializedName("subType") val subType: String,
    @SerializedName("uCode") val uCode: String,
    @SerializedName("contact_person_phone") val contactPersonPhone: String?,
    @SerializedName("ST_AsText(location)") val stAsTextLocation: String
)

data class ResponseModel(
    @SerializedName("places") val places: List<Place>
)
