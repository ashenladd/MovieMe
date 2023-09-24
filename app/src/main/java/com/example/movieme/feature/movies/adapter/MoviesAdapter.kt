package com.example.movieme.feature.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.movieme.databinding.ItemMovieBinding
import com.example.movieme.domain.model.MovieModel

class MoviesAdapter : ListAdapter<MovieModel, MoviesViewHolder>(
    MoviesDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}