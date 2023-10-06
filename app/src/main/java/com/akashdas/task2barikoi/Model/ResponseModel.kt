package com.akashdas.task2barikoi.Model

data class Place(
    val id: Long,
    val name: String,
    val distance_in_meters: Double,
    val longitude: String,
    val latitude: String,
    val Address: String,
    val city: String,
    val area: String,
    val pType: String,
    val subType: String,
    val uCode: String,
    val contact_person_phone: String?,
    val ST_AsText_location: String
)

data class ResponseModel( val places: List<Place>)
