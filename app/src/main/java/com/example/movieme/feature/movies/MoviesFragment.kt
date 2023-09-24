package com.example.movieme.feature.movies

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
import com.example.movieme.databinding.FragmentMoviesBinding
import com.example.movieme.domain.model.MovieModel
import com.example.movieme.feature.movies.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    val binding get() = _binding!!

    private val mViewModel: MoviesViewModel by viewModels()
    private val mMoviesAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(
            view,
            savedInstanceState
        )

        setupAdapter()
        setupObserver()
        setupClickListener()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mViewModel.viewState.collectLatest {
                        observeItemState(it.movies)
                        binding.apply {
                            tvPage.text = it.page.toString()
                        }
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

    private fun observeEffect(it: MoviesViewEffect) {

    }

    private fun observeItemState(moviesModels: List<MovieModel>) {
        mMoviesAdapter.submitList(moviesModels)
    }

    private fun setupAdapter() {
        binding.apply {
            rvMovies.layoutManager = LinearLayoutManager(requireContext())
            rvMovies.adapter = mMoviesAdapter
        }
    }

    private fun setupClickListener(){
        binding.apply {
            ibLeftArrow.setOnClickListener {
                mViewModel.onEvent(MoviesViewEvent.PrevPage)
            }
            ibRightArrow.setOnClickListener {
                mViewModel.onEvent(MoviesViewEvent.NextPage)
            }
        }
    }
}