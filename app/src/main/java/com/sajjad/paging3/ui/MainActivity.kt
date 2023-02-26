package com.sajjad.paging3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sajjad.paging3.databinding.ActivityMainBinding
import com.sajjad.paging3.ui.adapter.MovieAdapter
import com.sajjad.paging3.ui.adapter.MyLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var adapter: MovieAdapter

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvMovies.adapter = adapter

        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = false
            adapter.refresh()
        }

        binding.rvMovies.adapter = adapter.withLoadStateFooter(
            MyLoadStateAdapter {
                adapter.retry()
            }
        )

        lifecycleScope.launchWhenCreated {
            viewModel.movieList.collect {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect {
                val state = it.refresh
                binding.cvLoading.isVisible = state is LoadState.Loading
                binding.tvError.isVisible = state is LoadState.Error
            }
        }

    }
}