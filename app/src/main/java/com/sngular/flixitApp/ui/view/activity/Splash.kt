package com.sngular.flixitApp.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sngular.flixitApp.R
import com.sngular.flixitApp.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}