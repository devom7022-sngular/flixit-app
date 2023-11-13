package com.sngular.flixitApp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.sngular.flixitApp.databinding.ActivityMainBinding
import com.sngular.flixitApp.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel.onCreate()

        movieViewModel.popularMovies.observe(this) {
            Log.i("lista", it.toString())
            binding.tvMovie.text = it.toString()
        }

        movieViewModel.isLoading.observe(this) {
            binding.pbLoading.isVisible = it
        }
    }
}