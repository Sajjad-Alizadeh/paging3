package com.sajjad.paging3.data.model

data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val genres: List<String>,
    val images: List<String>
)