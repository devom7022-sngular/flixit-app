package com.sngular.flixitApp.ui.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.sngular.flixitApp.R
import com.sngular.flixitApp.databinding.FragmentPopularBinding
import com.sngular.flixitApp.databinding.FragmentProfileBinding
import com.sngular.flixitApp.ui.viewmodel.MovieViewModel
import com.sngular.flixitApp.ui.viewmodel.PersonViewModel
import com.sngular.flixitApp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val personViewModel: PersonViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.runOnUiThread {
            if (!requireActivity().isFinishing && isAdded) {
                personViewModel.getInfo()
            }
        }

        personViewModel.personData.observe(requireActivity()) {
            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                val person = it[0]

                activity?.runOnUiThread {
                    if (!requireActivity().isFinishing && isAdded) {
                        Glide.with(view)
                            .load("${Constants.BASE_URL_IMAGE}${person.profilePath}")
                            .apply(
                                RequestOptions().override(
                                    Constants.IMAGE_WIDTH,
                                    Constants.IMAGE_HEIGHT
                                )
                            )
                            .into(binding.ivPoster)

                        binding.tvTitle.text = person.name
                        binding.tvAverage.text = person.knownForDepartment
                        binding.tvRate.text = if (person.gender == "2") "Hombre" else "Mujer"
                        binding.tvUsers.text = person.popularity
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}