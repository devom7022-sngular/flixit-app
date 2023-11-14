package com.sngular.flixitApp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sngular.flixitApp.R
import com.sngular.flixitApp.databinding.ActivityMainBinding
import com.sngular.flixitApp.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

//    private val movieViewModel: MovieViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.frContainer)

        binding.btmNavView.setupWithNavController(navController)

        /*movieViewModel.onCreate()

        movieViewModel.popularMovies.observe(this) {
            Log.i("lista", it.toString())
            //binding.tvMovie.text = it.toString()
        }*/

        /*movieViewModel.isLoading.observe(this) {
            //binding.pbLoading.isVisible = it
        }*/
    }
}