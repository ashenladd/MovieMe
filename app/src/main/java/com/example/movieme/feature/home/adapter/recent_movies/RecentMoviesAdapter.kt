package com.example.movieme.feature.home.adapter.recent_movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.movieme.databinding.ItemMovieHorizontalBinding
import com.example.movieme.domain.model.MovieModel


class RecentMoviesAdapter : ListAdapter<MovieModel, RecentMoviesViewHolder>(
    RecentMoviesDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentMoviesViewHolder {
        return RecentMoviesViewHolder(
            ItemMovieHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
            )
        )
    }

    override fun onBindViewHolder(holder: RecentMoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}