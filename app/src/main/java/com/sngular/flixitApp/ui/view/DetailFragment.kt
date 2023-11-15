package com.sngular.flixitApp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sngular.flixitApp.R
import com.sngular.flixitApp.databinding.FragmentDetailBinding
import com.sngular.flixitApp.databinding.FragmentPopularBinding
import com.sngular.flixitApp.domain.model.bo.MovieBo
import com.sngular.flixitApp.util.Constants

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false);
        val view = binding.root;

        arguments?.let {
            val model = it.get("movieBo")
            if (model != null) {
                val movieDetail: MovieBo = it.getParcelable<MovieBo>("movieBo")?.toBo()!!
                binding.tvTitle.text = movieDetail.originalTitle
                binding.tvRelease.text = movieDetail.releaseDate
                binding.tvResumeOverview.text = movieDetail.overview

                Glide.with(this).load("${Constants.BASE_URL_IMAGE}${movieDetail.posterPath}")
                    .apply(RequestOptions().override(Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT))
                    .into(binding.ivPoster)

                Glide.with(this).load("${Constants.BASE_URL_IMAGE}${movieDetail.backdropPath}")
                    .apply(RequestOptions())
                    .into(binding.ivHeader)

            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}