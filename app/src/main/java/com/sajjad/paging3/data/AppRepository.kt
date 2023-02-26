package com.sajjad.paging3.data

import com.sajjad.paging3.data.response.MovieResponse
import retrofit2.Response
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getMovies(page: Int): Response<MovieResponse> {
        return apiService.getMovies(page)
    }
}