package com.example.movieme.feature.home.adapter.recent_movies

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieme.databinding.ItemMovieHorizontalBinding
import com.example.movieme.domain.model.MovieModel
import com.example.movieme.feature.home.HomeListener

class RecentMoviesViewHolder(private val binding: ItemMovieHorizontalBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        model: MovieModel,
        listener: HomeListener,
    ) {
        binding.apply {
            clMovie.setOnClickListener {
                listener.navigateToMovieDetail(model.id ?: -1)
            }
            tvTitle.text = model.title
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/original${model.posterPath}")
                .into(ivMovie)
        }
    }
}