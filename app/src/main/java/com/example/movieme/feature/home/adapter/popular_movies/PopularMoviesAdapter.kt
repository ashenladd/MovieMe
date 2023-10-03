package com.example.movieme.feature.home.adapter.popular_movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.movieme.databinding.ItemMovieVerticalBinding
import com.example.movieme.domain.model.MovieModel
import com.example.movieme.feature.home.HomeListener

class PopularMoviesAdapter(private val listener: HomeListener) :
    ListAdapter<MovieModel, PopularMoviesViewHolder>(
        PopularMoviesDiffUtil()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(
            ItemMovieVerticalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        holder.bind(getItem(position),listener)
    }
}