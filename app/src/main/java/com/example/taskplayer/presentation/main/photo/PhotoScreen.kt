package com.example.taskplayer.presentation.main.photo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitTouchSlopOrCancellation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.taskplayer.data.local.UserSessionManager
import java.io.File
import java.io.FileInputStream

@Composable
fun PhotoScreen(
    path: String,
    onBack: () -> Unit,
    onDelete: () -> Unit
) {
    // Состояние для масштаба
    var scale by remember { mutableFloatStateOf(1f) }

    // Загрузка изображения
    val bitmap by remember(path) { derivedStateOf { loadBitmapFromStorage(path) } }
    val context = LocalContext.current
    val tokenManager = remember { UserSessionManager(context) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        scale = if (scale == 1f) 2f else 1f
                    }
                )
            }
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        val down = awaitFirstDown()
                        val startX = down.position.x
                        val change = awaitTouchSlopOrCancellation(down.id) { change, _ ->
                            change.consume()
                        }

                        change?.let {
                            val endX = it.position.x
                            val deltaX = endX - startX

                            when {
                                deltaX > 150f -> onBack()
                                deltaX < -150f -> {
                                    deleteImage(path)
                                    tokenManager.getGallery()
                                        .filter { it.path != path }
                                        .let { tokenManager.saveGallery(it) }
                                    onDelete()
                                }
                            }
                        }
                    }
                }
            }
    ) {
        when {
            bitmap != null -> {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = "Просмотр фото",
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale
                        ),
                    contentScale = ContentScale.Fit
                )
            }
            else -> {
                Text(
                    text = "Не удалось загрузить изображение",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // Кнопка закрыть
        Button(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Text("Закрыть")
        }

        // Кнопка удалить
        Button(
            onClick = {
                deleteImage(path)
                tokenManager.getGallery()
                    .filter { it.path != path }
                    .let { tokenManager.saveGallery(it) }
                onDelete()
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text("Удалить")
        }
    }
}

fun loadBitmapFromStorage(path: String): Bitmap? {
    return try {
        File(path).takeIf { it.exists() }?.let {
            BitmapFactory.decodeStream(FileInputStream(it))
        }
    } catch (e: Exception) {
        null
    }
}

private fun deleteImage(path: String) {
    try {
        File(path).takeIf { it.exists() }?.delete()
    } catch (e: Exception) {
        Log.e("PhotoScreen", "Ошибка при удалении изображения", e)
    }
}