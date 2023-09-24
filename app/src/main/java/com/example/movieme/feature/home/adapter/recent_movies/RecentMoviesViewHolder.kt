package com.example.movieme.feature.home.adapter.recent_movies

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieme.databinding.ItemMovieHorizontalBinding
import com.example.movieme.databinding.ItemMovieVerticalBinding
import com.example.movieme.domain.model.MovieModel

class RecentMoviesViewHolder (private val binding: ItemMovieHorizontalBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        model: MovieModel,
    ) {
        binding.apply {
            tvTitle.text = model.title
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/original${model.posterPath}")
                .into(ivMovie)
        }
    }
}