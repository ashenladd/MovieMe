package com.example.movieme.feature.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.movieme.databinding.ItemMovieVerticalBinding
import com.example.movieme.domain.model.MovieModel
import com.example.movieme.feature.movies.MoviesListener

class MoviesAdapter(private val listener: MoviesListener) :
    ListAdapter<MovieModel, MoviesViewHolder>(
        MoviesDiffUtil()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieVerticalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position),listener)
    }

}