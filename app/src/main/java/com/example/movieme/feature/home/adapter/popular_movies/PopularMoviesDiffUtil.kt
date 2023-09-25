package com.example.movieme.feature.home.adapter.popular_movies

import androidx.recyclerview.widget.DiffUtil
import com.example.movieme.domain.model.MovieModel

class PopularMoviesDiffUtil  : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem == newItem
    }
}