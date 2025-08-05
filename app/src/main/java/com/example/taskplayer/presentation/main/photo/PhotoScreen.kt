package com.example.taskplayer.presentation.main.photo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.navigation.NavController
import com.example.taskplayer.data.local.UserSessionManager
import java.io.File
import java.io.FileInputStream

@Composable
fun PhotoScreen(
    path: String,
    navController: NavController,
    tokenManager: UserSessionManager
) {
    // Состояние для масштаба
    val scale = remember { mutableStateOf(1f) }

    // Загрузка изображения по пути
    val bitmap = remember(path) { loadBitmapFromStorage(path) }

    // Функция для удаления фото
    fun deletePhoto() {
        deleteImage(path)
        val updatedGallery = tokenManager.getGallery().filter { it.path != path }
        tokenManager.saveGallery(updatedGallery)
        navController.popBackStack()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            // Двойной тап для увеличения/уменьшения масштаба
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        scale.value = if (scale.value == 1f) 2f else 1f
                    }
                )
            }
            // Свайпы влево/вправо
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
                                deltaX > 150f -> navController.popBackStack() // Свайп вправо - закрыть
                                deltaX < -150f -> deletePhoto() // Свайп влево - удалить
                            }
                        }
                    }
                }
            }
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Фото",
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = scale.value,
                        scaleY = scale.value
                    ),
                contentScale = ContentScale.Fit
            )
        } else {
            Text(
                text = "Не удалось загрузить изображение",
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Кнопка закрыть
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Text("Закрыть")
        }

        // Кнопка удалить
        Button(
            onClick = { deletePhoto() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text("Удалить")
        }
    }
}

// Функция для загрузки изображения из хранилища
 fun loadBitmapFromStorage(path: String): Bitmap? {
    return try {
        val file = File(path)
        if (file.exists()) {
            BitmapFactory.decodeStream(FileInputStream(file))
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}

// Функция для удаления изображения
private fun deleteImage(path: String) {
    try {
        File(path).takeIf { it.exists() }?.delete()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}