package com.example.catmemebuilder.data.remote.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CataasResponse(
    val id : String,
    val created_at : String,
    val tags : List<String>,
    val url : String
)
