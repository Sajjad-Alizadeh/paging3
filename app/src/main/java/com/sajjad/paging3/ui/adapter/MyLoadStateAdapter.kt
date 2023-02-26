package com.sajjad.paging3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sajjad.paging3.databinding.ViewMoreItemLoadingBinding

class MyLoadStateAdapter constructor(private val retry:() -> Unit): LoadStateAdapter<MyLoadStateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = ViewMoreItemLoadingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class ViewHolder(private val binding: ViewMoreItemLoadingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: LoadState) {
            binding.loading.isVisible = data is LoadState.Loading
            binding.btnRetry.isVisible = data is LoadState.Error
            binding.btnRetry.setOnClickListener {
                retry()
            }
        }
    }

}