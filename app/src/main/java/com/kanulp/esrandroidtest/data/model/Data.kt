package com.kanulp.esrandroidtest.data.model

data class Data(
    val category: String,
    val destination: Destination,
    val distance: Int,
    val duration: Int,
    val origin: Origin,
    val path: Any,
    val polyline: String
)