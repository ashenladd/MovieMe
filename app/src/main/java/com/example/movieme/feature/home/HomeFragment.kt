package com.example.movieme.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieme.databinding.FragmentHomeBinding
import com.example.movieme.domain.model.MovieModel
import com.example.movieme.feature.home.adapter.popular_movies.PopularMoviesAdapter
import com.example.movieme.feature.home.adapter.recent_movies.RecentMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    private val mViewModel: HomeViewModel by viewModels()

    private val mPopularMoviesAdapter: PopularMoviesAdapter by lazy {
        PopularMoviesAdapter()
    }

    private val mRecentMoviesAdapter: RecentMoviesAdapter by lazy {
        RecentMoviesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(
            view,
            savedInstanceState
        )

        setupAdapter()
        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mViewModel.viewState.collectLatest {
                        observeItemPopularState(it.popularMovies)
                        observeItemRecentState(it.recentMovies)
                    }
                }
                launch {
                    mViewModel.viewEffect.collectLatest {
                        observeEffect(it)
                    }
                }

            }
        }
    }

    private fun observeEffect(it: HomeViewEffect) {

    }

    private fun observeItemPopularState(moviesModels: List<MovieModel>) {
        mPopularMoviesAdapter.submitList(moviesModels)
    }

    private fun observeItemRecentState(moviesModels: List<MovieModel>) {
        mRecentMoviesAdapter.submitList(moviesModels)
    }

    private fun setupAdapter() {
        binding.apply {
            rvMoviesHorizontal.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            rvMoviesHorizontal.adapter = mRecentMoviesAdapter

            rvMoviesVertical.layoutManager = LinearLayoutManager(
                requireContext(),
            )
            rvMoviesVertical.adapter = mPopularMoviesAdapter
        }
    }
}