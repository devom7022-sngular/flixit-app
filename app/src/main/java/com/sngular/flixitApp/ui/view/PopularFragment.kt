package com.sngular.flixitApp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.sngular.flixitApp.R
import com.sngular.flixitApp.databinding.FragmentPopularBinding
import com.sngular.flixitApp.domain.model.bo.MovieBo
import com.sngular.flixitApp.ui.adapter.MoviesAdapter
import com.sngular.flixitApp.ui.viewmodel.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var rateMoviesAdapter: MoviesAdapter
    private lateinit var upcomingMoviesAdapter: MoviesAdapter

    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularBinding.inflate(inflater, container, false);

        setupRecycler()
        movieViewModel.onCreate()
        movieViewModel.popularMovies.observe(requireActivity()) {
            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                moviesAdapter.list = it
                moviesAdapter.notifyDataSetChanged()
            }

        }

        movieViewModel.rateMovies.observe(requireActivity()) {
            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                rateMoviesAdapter.list = it
                rateMoviesAdapter.notifyDataSetChanged()
            }

        }
        movieViewModel.upcomingMovies.observe(requireActivity()) {
            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                upcomingMoviesAdapter.list = it
                upcomingMoviesAdapter.notifyDataSetChanged()
            }

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler() {
        val lmPopular = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularMovies.layoutManager = lmPopular
        moviesAdapter = MoviesAdapter(requireActivity(), arrayListOf()) {
            launchDetail(it)
        }
        binding.rvPopularMovies.adapter = moviesAdapter

        val lmRate = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRateMovies.layoutManager = lmRate
        rateMoviesAdapter = MoviesAdapter(requireActivity(), arrayListOf()) {
            launchDetail(it)
        }
        binding.rvRateMovies.adapter = rateMoviesAdapter

        val lmUpcoming = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvUpcomingMovies.layoutManager = lmUpcoming
        upcomingMoviesAdapter = MoviesAdapter(requireActivity(), arrayListOf()) {
            launchDetail(it)
        }
        binding.rvUpcomingMovies.adapter = upcomingMoviesAdapter
    }

    private fun launchDetail(it: MovieBo) {
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_popular_nav_to_detail_nav, bundleOf("movieBo" to it))
        }
    }

}