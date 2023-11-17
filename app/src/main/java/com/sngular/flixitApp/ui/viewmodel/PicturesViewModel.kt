package com.sngular.flixitApp.ui.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.UploadTask
import com.sngular.flixitApp.domain.model.bo.LocationBo
import com.sngular.flixitApp.domain.usecase.GetPersonUseCase
import com.sngular.flixitApp.domain.usecase.GetPicturesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PicturesViewModel @Inject constructor(
    private val getPicturesUseCase: GetPicturesUseCase
) :ViewModel(){
    fun savePhoto(uri: Uri, isSuccessCallback: (UploadTask.TaskSnapshot?) -> Unit) {
        viewModelScope.launch {
            getPicturesUseCase.savePhoto(uri, isSuccessCallback)
        }

    }

    fun getGallery(callback: (MutableList<String>?) -> Unit) {
        viewModelScope.launch {
            getPicturesUseCase.getGallery(callback)
        }
    }

}