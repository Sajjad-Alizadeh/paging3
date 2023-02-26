package com.sajjad.paging3.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sajjad.paging3.data.AppRepository
import com.sajjad.paging3.data.model.Movie
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val repository: AppRepository
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getMovies(currentPage)
            val data = response.body()?.data ?: emptyList()
            val responseData = mutableListOf<Movie>()
            responseData.addAll(data)
            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}