package com.example.taskplayer.presentation.main.photo


data class GalleryItem(
    val path: String,
    val timestamp: Long = System.currentTimeMillis()
)
