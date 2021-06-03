package com.kanulp.esrandroidtest.data.model

data class Payload(
    val feed: String,
    val items: List<Item>,
    val page: Int,
    val totalItemCount: Int,
    val totalPages: Int
)