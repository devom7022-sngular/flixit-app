package com.sngular.flixitApp.data.repository.remote

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.net.toFile
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.sngular.flixitApp.R
import java.io.File
import java.lang.Math.abs
import java.util.UUID
import javax.inject.Inject

class PictureRemoteRepository @Inject constructor(
    val storage: FirebaseStorage,
    val storageRef: StorageReference,
    private val context: Context?
) {
    fun savePhoto(uri: Uri, isSuccessCallback: (UploadTask.TaskSnapshot?) -> Unit) {

        uri.let {
            val fileUT = Uri.fromFile(uri.toFile())
            val imagesRef = storageRef.child("images/${fileUT.lastPathSegment}")
            val uploadTask: UploadTask = imagesRef.putFile(fileUT)

            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
                Log.i("UploadTask Exception", it.toString())

                isSuccessCallback(null)
            }.addOnSuccessListener { taskSnapshot ->
                Log.i("UploadTask Success", taskSnapshot.toString())

                isSuccessCallback(taskSnapshot)
            }
        }

    }

    var data: MutableList<String> = mutableListOf()
    fun getGallery(callback: (MutableList<String>?) -> Unit) {
        val list = storageRef.child("images/").listAll()

        list.addOnSuccessListener {
            var x = 0
            it.items.map { sr ->
                sr.downloadUrl.addOnSuccessListener { uri ->
                    val url = uri.toString()
                    setData(url)
                    if (x == it.items.size - 1) {
                        callback(data)
                    }
                    x++
                }


            }
        }.addOnFailureListener {
            callback(null)
        }
    }

    fun setData(url: String) {
        data.add(url)
    }
}
