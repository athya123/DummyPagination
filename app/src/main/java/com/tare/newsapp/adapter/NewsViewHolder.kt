package com.tare.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tare.newsapp.R
import com.tare.newsapp.databinding.ItemNewsBinding
import com.tare.newsapp.pojo.entities.Article
import com.tare.newsapp.ui.HomeViewModel

class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(currentItem: Article?, homeViewModel: HomeViewModel) {
        currentItem?.let {
            binding.item = it
            binding.viewModel = homeViewModel
            binding.executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val binding: ItemNewsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_news, parent, false
            )
            return NewsViewHolder(binding)
        }
    }
}