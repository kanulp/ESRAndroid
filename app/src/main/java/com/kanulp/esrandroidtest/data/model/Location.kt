package com.kanulp.esrandroidtest.data.model

data class Location(
    val city: String,
    val country: String,
    val countryCode: String,
    val geohash: String,
    val isDefaultLocation: Boolean,
    val lastUpdated: Int,
    val lat: Double,
    val lng: Double,
    val postalCode: String,
    val state: String,
    val stateCode: String,
    val streetName: String,
    val streetNumber: String,
    val userFriendlyLocation: String
)