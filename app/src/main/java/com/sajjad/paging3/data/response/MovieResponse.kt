package com.sajjad.paging3.data.response

import com.sajjad.paging3.data.model.Metadata
import com.sajjad.paging3.data.model.Movie

data class MovieResponse(
    val data: List<Movie>,
    val metadata: Metadata
)