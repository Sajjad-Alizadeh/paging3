package com.sajjad.paging3.data

import com.sajjad.paging3.data.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/v1/movies")
    suspend fun getMovies(
        @Query("page") page: Int
    ): Response<MovieResponse>

}