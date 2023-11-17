package com.sngular.flixitApp.data.repository.remote

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.net.toFile
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class PictureRemoteRepository @Inject constructor(
    val storage: FirebaseStorage,
    val storageRef: StorageReference,
    private val context: Context?
) {
    fun savePhoto(
        uri: Uri,
        isPhoto: Boolean,
        isSuccessCallback: (UploadTask.TaskSnapshot?) -> Unit
    ) {

        uri.let {
            val uploadTask: UploadTask = if (isPhoto) {
                val fileUT = Uri.fromFile(uri.toFile())
                val imagesRef = storageRef.child("images/${fileUT.lastPathSegment}")
                imagesRef.putFile(fileUT)
            } else {
                val iStream: InputStream = context!!.contentResolver.openInputStream(uri)!!
                val bytes = getBytes(iStream)
                val imagesRef = storageRef.child("images/${uri.lastPathSegment}")
                imagesRef.putBytes(bytes!!)
            }

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

    @Throws(IOException::class)
    fun getBytes(inputStream: InputStream): ByteArray? {
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)
        var len = 0
        while (inputStream.read(buffer).also { len = it } != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        return byteBuffer.toByteArray()
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
