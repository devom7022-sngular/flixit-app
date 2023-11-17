package com.sngular.flixitApp.data.repository

import android.net.Uri
import com.google.firebase.storage.UploadTask
import com.sngular.flixitApp.data.repository.remote.PictureRemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PicturesRepository @Inject constructor(private val remote: PictureRemoteRepository) {

    fun savePhoto(uri: Uri, isPhoto: Boolean, isSuccessCallback: (UploadTask.TaskSnapshot?) -> Unit) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            remote.savePhoto(uri,isPhoto, isSuccessCallback)
        }
    }

    fun getGallery(callback: (MutableList<String>?) -> Unit) {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            remote.getGallery(callback)
        }
    }
}