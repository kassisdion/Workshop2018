package com.workshop.workshop.api.response

import com.squareup.moshi.Json


data class ObjectModel(
        @Json(name = "albumId")
        val albumId: Long,

        @Json(name = "id")
        val id: Long,

        @Json(name = "title")
        val title: String,

        @Json(name = "url")
        val url: String,

        @Json(name = "thumbnailUrl")
        val thumbnailUrl: String
)