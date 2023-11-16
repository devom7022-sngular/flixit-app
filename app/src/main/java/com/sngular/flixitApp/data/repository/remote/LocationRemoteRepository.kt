package com.sngular.flixitApp.data.repository.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.sngular.flixitApp.domain.model.bo.LocationBo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationRemoteRepository @Inject constructor(val db: FirebaseFirestore) {

    suspend fun getLocations(callback: (MutableList<LocationBo>) -> Unit) {

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            db.collection("locations")
                .get()
                .addOnSuccessListener { result ->
                    run {
                        var response: MutableList<LocationBo> = mutableListOf()
                        for (document in result) {
                            response.add(
                                LocationBo(
                                    long = document.data["lat"].toString(),
                                    lat = document.data["long"].toString(),
                                    register = document.data["register"].toString(),
                                )
                            )
                        }
                        callback(response)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
        }
    }
}