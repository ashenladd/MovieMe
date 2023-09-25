package com.example.movieme.feature.home.adapter.recent_movies

import androidx.recyclerview.widget.DiffUtil
import com.example.movieme.domain.model.MovieModel

class RecentMoviesDiffUtil : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem == newItem
    }
}