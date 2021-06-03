package com.kanulp.esrandroidtest.data.model

data class Item(
    val author: Author,
    val body: Any,
    val coverImage: String,
    val created: String,
    val createdEpoch: Long,
    val createdVia: String,
    val ctype: String,
    val `data`: Data,
    val displayImage: DisplayImage,
    val id: Int,
    val location: Location,
    val metaInfo: MetaInfo,
    val modified: String,
    val modifiedEpoch: Long,
    val motorcycle: Any,
    val permalink: String,
    val precis: String,
    val privacyLevel: Int,
    val sort: String,
    val source: String,
    val title: String,
    val url: String,
    val userMetaInfo: UserMetaInfo
)