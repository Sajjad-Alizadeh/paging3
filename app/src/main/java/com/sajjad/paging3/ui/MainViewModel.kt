package com.sajjad.paging3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sajjad.paging3.data.paging.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val moviePagingSource: MoviePagingSource
) : ViewModel() {
    val movieList = Pager(PagingConfig(1)) {
        moviePagingSource
    }.flow.cachedIn(viewModelScope)
}