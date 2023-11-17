package com.sngular.flixitApp.domain.usecase

import android.net.Uri
import com.google.firebase.storage.UploadTask
import com.sngular.flixitApp.data.repository.LocationRepository
import com.sngular.flixitApp.data.repository.PicturesRepository
import javax.inject.Inject

class GetPicturesUseCase @Inject constructor(private val repository: PicturesRepository) {
    fun savePhoto(uri: Uri, isSuccessCallback: (UploadTask.TaskSnapshot?) -> Unit) {
        repository.savePhoto(uri, isSuccessCallback)
    }

    fun getGallery(callback: (MutableList<String>?) -> Unit) {
        repository.getGallery(callback)
    }
}