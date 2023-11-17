package com.sngular.flixitApp.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.UploadTask
import com.sngular.flixitApp.domain.usecase.GetPicturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    private val getPicturesUseCase: GetPicturesUseCase
) :ViewModel(){
    fun savePhoto(uri: Uri,isPhoto: Boolean, isSuccessCallback: (UploadTask.TaskSnapshot?) -> Unit) {
        viewModelScope.launch {
            getPicturesUseCase.savePhoto(uri,isPhoto, isSuccessCallback)
        }

    }

    fun getGallery(callback: (MutableList<String>?) -> Unit) {
        viewModelScope.launch {
            getPicturesUseCase.getGallery(callback)
        }
    }

}