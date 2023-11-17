package com.sngular.flixitApp.data.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.UploadTask
import com.sngular.flixitApp.data.repository.local.LocationLocalRepository
import com.sngular.flixitApp.data.repository.local.MovieLocalRepository
import com.sngular.flixitApp.data.repository.remote.LocationRemoteRepository
import com.sngular.flixitApp.data.repository.remote.MovieRemoteRepository
import com.sngular.flixitApp.data.repository.remote.PictureRemoteRepository
import com.sngular.flixitApp.domain.model.bo.LocationBo
import com.sngular.flixitApp.domain.model.bo.PersonBo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PicturesRepository @Inject constructor(private val remote: PictureRemoteRepository) {

    fun savePhoto(uri: Uri, isSuccessCallback: (UploadTask.TaskSnapshot?) -> Unit) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            remote.savePhoto(uri, isSuccessCallback)
        }
    }

    fun getGallery(callback: (MutableList<String>?) -> Unit) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            remote.getGallery(callback)
        }
    }
}