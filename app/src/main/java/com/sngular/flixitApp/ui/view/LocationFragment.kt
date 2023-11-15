package com.sngular.flixitApp.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.Timestamp
import com.google.firebase.firestore.AggregateSource
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.PersistentCacheSettings
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ServerTimestamp
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.memoryCacheSettings
import com.google.firebase.firestore.persistentCacheSettings
import com.google.firebase.firestore.toObject
import com.google.firebase.Firebase

import com.sngular.flixitApp.R
import com.sngular.flixitApp.databinding.FragmentLocationBinding
import com.sngular.flixitApp.databinding.FragmentPopularBinding
import com.sngular.flixitApp.domain.model.bo.LocationBo
import com.sngular.flixitApp.ui.viewmodel.LocationViewModel
import com.sngular.flixitApp.ui.viewmodel.MovieViewModel

class LocationFragment : Fragment() {

    private var _binding: FragmentLocationBinding? = null
    private lateinit var locationList: List<LocationBo>
    private val binding get() = _binding!!
    private val locationViewModel: LocationViewModel by activityViewModels()

    private val callback = OnMapReadyCallback { googleMap ->
        locationList.map {
            val loc = LatLng(it.lat.toDouble(), it.long.toDouble())
            googleMap.addMarker(MarkerOptions().position(loc).title(it.register))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationBinding.inflate(inflater, container, false);
        val view = binding.root;

        locationViewModel.onCreate()


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        locationViewModel.locations.observe(requireActivity()) {
            locationList = it
            mapFragment?.getMapAsync(callback)
        }


        /*val locations = db.collection("locations")
        Log.i("locations", locations.toString())*/

    }
}