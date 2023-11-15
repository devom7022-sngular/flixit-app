package com.sngular.flixitApp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.sngular.flixitApp.R
import com.sngular.flixitApp.databinding.ActivityMainBinding
import com.sngular.flixitApp.databinding.FragmentPopularBinding
import com.sngular.flixitApp.ui.adapter.MoviesAdapter
import com.sngular.flixitApp.ui.viewmodel.MovieViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!
    private lateinit var moviesAdapter: MoviesAdapter

    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularBinding.inflate(inflater, container, false);
        val view = binding.root;

        setupRecycler()
        movieViewModel.onCreate()
        movieViewModel.popularMovies.observe(requireActivity()) {
            Log.i("lista", it.toString())
            moviesAdapter.list = it
            moviesAdapter.notifyDataSetChanged()
        }
        /*
                movieViewModel.rateMovies.observe(requireActivity()) {
                    val scope = CoroutineScope(Dispatchers.IO)
                    scope.launch {
                        Log.i("rate", it.toString())
                        rateMoviesAdapter.list = it
                        rateMoviesAdapter.notifyDataSetChanged()
                    }

                }
                movieViewModel.upcomingMovies.observe(requireActivity()) {
                    val scope = CoroutineScope(Dispatchers.IO)
                    scope.launch {
                        Log.i("upcoming", it.toString())
                        upcomingMoviesAdapter.list = it
                        upcomingMoviesAdapter.notifyDataSetChanged()
                    }

                }
         */

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler() {
        val layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,true)
        binding.rvPopularMovies.layoutManager = layoutManager
        moviesAdapter = MoviesAdapter(requireActivity(), arrayListOf())
        binding.rvPopularMovies.adapter = moviesAdapter
    }
}