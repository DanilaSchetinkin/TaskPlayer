package com.example.taskplayer.presentation.main.profile

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.net.Uri
import com.example.taskplayer.data.local.UserSessionManager
import com.example.taskplayer.presentation.main.photo.GalleryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class ProfileViewModel(private val userSessionManager: UserSessionManager) : ViewModel() {
    private val _gallery = MutableStateFlow<List<GalleryItem>>(emptyList())
    val gallery: StateFlow<List<GalleryItem>> = _gallery

    init {
        loadGallery()
    }

    fun loadGallery() {
        viewModelScope.launch(Dispatchers.IO) {
            val items = userSessionManager.getGallery()
            _gallery.value = items
        }
    }

    fun addImage(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val path = saveImageToInternalStorage(context, uri)
            path?.let {
                val newGallery = _gallery.value + GalleryItem(it)
                _gallery.value = newGallery
                userSessionManager.saveGallery(newGallery)
            }
        }
    }

    private fun saveImageToInternalStorage(context: Context, uri: Uri): String? {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val fileName = "img_${System.currentTimeMillis()}.jpg"
            val file = File(context.filesDir, fileName)
            val outputStream = FileOutputStream(file)

            inputStream?.copyTo(outputStream)

            inputStream?.close()
            outputStream.close()

            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}