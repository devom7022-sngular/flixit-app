package com.sngular.flixitApp.data.repository.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sngular.flixitApp.data.model.dto.LocationDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationRemoteRepository @Inject constructor(val db: FirebaseFirestore) {


    suspend fun getLocations(): List<LocationDto> {


        val call = db.collection("locations")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        Log.i("Log", call.toString())
        /*return withContext(Dispatchers.IO) {
            val response: List<LocationDto> = data()
            response
        }*/
        return data()
    }

    fun data(): List<LocationDto> {
        return arrayOf(
            LocationDto("10.0", "3.5", "2023 - 12 - 10"),
            LocationDto("29.0", "3", "2023 - 12 - 11"),
            LocationDto("19.0", "5", "2023 - 12 - 12"),
            LocationDto("90.0", "3.5", "2023 - 12 - 13"),
            LocationDto("19.0", "3.5", "2023 - 12 - 14")
        ).toList()
    }
}