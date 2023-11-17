package com.sngular.flixitApp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sngular.flixitApp.R
import com.sngular.flixitApp.databinding.FragmentDetailBinding
import com.sngular.flixitApp.databinding.FragmentGalleryBinding
import com.sngular.flixitApp.ui.adapter.GalleryAdapter
import com.sngular.flixitApp.ui.adapter.MoviesAdapter
import com.sngular.flixitApp.ui.viewmodel.MovieViewModel
import com.sngular.flixitApp.ui.viewmodel.PicturesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var galleryAdapter: GalleryAdapter

    private val picturesViewModel: PicturesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false);

        val lm = GridLayoutManager(activity, 3)
        binding.rvPictures.layoutManager = lm
        galleryAdapter = GalleryAdapter(requireActivity(), arrayListOf())
        binding.rvPictures.adapter = galleryAdapter

        picturesViewModel.getGallery(callback = {
            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                galleryAdapter.list = it!!
                galleryAdapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}