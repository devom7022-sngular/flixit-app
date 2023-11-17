package com.sngular.flixitApp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.sngular.flixitApp.R
import com.sngular.flixitApp.databinding.FragmentLocationBinding
import com.sngular.flixitApp.domain.model.bo.LocationBo
import com.sngular.flixitApp.ui.viewmodel.LocationViewModel
import com.sngular.flixitApp.util.valueToDouble
import com.sngular.flixitApp.util.valueToInt

class LocationFragment : Fragment() {

    private var _binding: FragmentLocationBinding? = null
    private lateinit var locationList: List<LocationBo>
    private val binding get() = _binding!!
    private val locationViewModel: LocationViewModel by activityViewModels()

    private val callback = OnMapReadyCallback { googleMap ->
        locationList.map {
            val lat = it.long.toFloat().toDouble()
            val lng = it.lat.toFloat().toDouble()
            val latLng = LatLng(lat, lng)
            googleMap.addMarker(MarkerOptions().position(latLng).title(it.register).flat(true))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationBinding.inflate(inflater, container, false);
        val view = binding.root;

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        locationViewModel.locations.observe(requireActivity()) {
            locationList = it
            mapFragment?.getMapAsync(callback)
        }
    }
}