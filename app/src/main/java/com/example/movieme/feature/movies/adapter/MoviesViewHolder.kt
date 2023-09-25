package com.example.movieme.feature.movies.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movieme.databinding.ItemMovieVerticalBinding
import com.example.movieme.domain.model.MovieModel

class MoviesViewHolder(val binding: ItemMovieVerticalBinding) : ViewHolder(binding.root) {
    fun bind(
        model: MovieModel,
    ) {
        binding.apply {
            tvTitle.text = model.title
            tvOverview.text = model.overview
            tvRating.text = model.voteAverage.toString()
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/original${model.posterPath}")
                .into(ivMovie)
        }
    }
}