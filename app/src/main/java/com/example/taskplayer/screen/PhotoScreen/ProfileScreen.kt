package com.example.taskplayer.screen.PhotoScreen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import android.net.Uri
import com.example.taskplayer.R
import com.example.taskplayer.data.local.TokenManager
import com.example.taskplayer.screen.MainScreen.MainAvatar
import com.example.taskplayer.screen.component.BottomBar
import com.example.taskplayer.ui.theme.DarkGreen
import com.example.taskplayer.ui.theme.MediaTheme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@Composable
fun ProfileScreen(tokenManager: TokenManager, navController: NavController) {
    val nickname = tokenManager.getNickName()
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        containerColor = DarkGreen,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(

                    painter = painterResource(R.drawable.tripalka),
                    contentDescription = "palki",
                    modifier = Modifier
                        .size(20.dp)
                )
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "LogoMain",
                    modifier = Modifier
                        .size(100.dp)
                )

                TextButton(onClick = { tokenManager.clearAuthData() }) {
                    Text(
                        text = "exit",
                        style = MediaTheme.typography.alegreyaSans15,
                        color = MediaTheme.colors.text
                    )
                }
            }
        },
        bottomBar = {
            BottomBar(
                currentRoute = "profile",
                onItemClick = { route ->
                    navController.navigate(route) {
                        popUpTo("profile") { inclusive = false }
                        launchSingleTop = true
                    }
                })
        }) { paddingValues ->
        ProfileScreenContent(paddingValues, tokenManager, nickname, navController)
    }
}

@Composable
fun ProfileScreenContent(
    paddingValues: PaddingValues,
    tokenManager: TokenManager,
    nickname: String,
    navController: NavController
) {
    val context = LocalContext.current
    val gallery = remember { mutableStateListOf<String>() }

    // Галерея из SharedPreferences
    LaunchedEffect(Unit) {
        gallery.clear()
        gallery.addAll(tokenManager.getGallery())
    }

    // Лаунчер для выбора изображения
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val savedPath = saveImageToInternalStorage(context, uri)
            if (savedPath != null) {
                gallery.add(savedPath)
                tokenManager.saveGallery(gallery)
            }
        }
    }

    Column(modifier = Modifier.padding(paddingValues)) {
        // Аватар и ник
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            MainAvatar(
                tokenManager = tokenManager,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = nickname,
                style = MediaTheme.typography.alegreyaBoldStart,
                color = MediaTheme.colors.text
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        // Галерея
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            items(gallery) { path ->
                Box(
                    modifier = Modifier
                        .clickable {
                            navController.navigate("photo_screen/$path")
                        }
                ) {
                    val imageBitmap = remember(path) {
                        loadBitmapFromStorage(path)
                    }
                    imageBitmap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(180.dp)
                        )
                    }
                }
            }

            // Последний элемент — кнопка "+"
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .clickable {
                            galleryLauncher.launch("image/*")
                        }
                ) {
                    Text(
                        text = "+",
                        style = MediaTheme.typography.alegreyaBoldStart,
                        color = MediaTheme.colors.text
                    )
                }
            }
        }
    }
}

fun saveImageToInternalStorage(context: Context, uri: Uri): String? {
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



