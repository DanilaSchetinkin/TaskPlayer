package com.example.taskplayer.presentation.main.photo

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
private fun GalleryItem(
    item: GalleryItem,
    onPhotoClick: (String) -> Unit, // Четко указываем тип параметра
    modifier: Modifier = Modifier,
    contentDescription: String = "Gallery photo",
    imageSize: Dp = 180.dp
) {
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(item.path) {
        imageBitmap = withContext(Dispatchers.IO) {
            loadBitmapFromStorage(item.path)
        }
    }

    Box(
        modifier = modifier
            .size(imageSize)
            .clickable { onPhotoClick(item.path) } // Прямая передача пути
    ) {
        imageBitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } ?: Placeholder() // Компонент-заглушка
    }
}

@Composable
private fun Placeholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}