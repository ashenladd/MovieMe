package com.example.movieme.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieme.databinding.FragmentHomeBinding
import com.example.movieme.domain.model.MovieModel
import com.example.movieme.feature.home.adapter.popular_movies.PopularMoviesAdapter
import com.example.movieme.feature.home.adapter.recent_movies.RecentMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeListener {

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    private val mViewModel: HomeViewModel by viewModels()

    private val mPopularMoviesAdapter: PopularMoviesAdapter by lazy {
        PopularMoviesAdapter(this)
    }

    private val mRecentMoviesAdapter: RecentMoviesAdapter by lazy {
        RecentMoviesAdapter(this)
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
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.apply {
            ibNarrowArrowRight1.setOnClickListener {
                navigatoToMovies("recent_movies")
            }
            ibNarrowArrowRight2.setOnClickListener {
                navigatoToMovies("populart_movies")
            }
        }
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

    override fun navigateToMovieDetail(movieId: Long) {
        try {
            val directions = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movieId)
            findNavController().navigate(directions)
        }catch (e:Exception){
            Toast.makeText(requireContext(),"${e.message}",Toast.LENGTH_SHORT).show()
        }
    }

    override fun navigatoToMovies(featureName: String) {
        try {
            val directions = HomeFragmentDirections.actionHomeFragmentToMoviesFragment(featureName)
            findNavController().navigate(directions)
        }catch (e:Exception){
            Toast.makeText(requireContext(),"${e.message}",Toast.LENGTH_SHORT).show()
        }
    }
}