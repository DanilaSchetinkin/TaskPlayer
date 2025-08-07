package com.example.taskplayer.presentation.main.profile

import android.content.Context
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.Bitmap
import coil3.compose.rememberAsyncImagePainter
import com.example.taskplayer.R
import com.example.taskplayer.data.local.UserSessionManager
import com.example.taskplayer.presentation.main.components.MainAvatar
import com.example.taskplayer.presentation.main.components.BottomBar
import com.example.taskplayer.core.theme.DarkGreen
import com.example.taskplayer.core.theme.MediaTheme
import com.example.taskplayer.presentation.main.photo.GalleryItem
import com.example.taskplayer.presentation.main.photo.loadBitmapFromStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

@Composable
fun ProfileScreen(
    onNavigateToPhoto: (String) -> Unit,
    modifier: Modifier = Modifier,
    tokenManager: UserSessionManager
) {
    val viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(tokenManager)
    )

    val context = LocalContext.current
    val gallery by viewModel.gallery.collectAsState()
    val nickname = tokenManager.getNickName()

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.addImage(context, uri)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxWidth(),
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
                    modifier = Modifier.size(20.dp)
                )
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "LogoMain",
                    modifier = Modifier.size(100.dp)
                )
                TextButton(onClick = { tokenManager.clearAuthData() }) {
                    Text(
                        text = "exit",
                        style = MediaTheme.typography.alegreyaSans15,
                        color = MediaTheme.colors.text
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                MainAvatar(
                    tokenManager = tokenManager,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                )
            }

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = nickname,
                    style = MediaTheme.typography.alegreyaBoldStart,
                    color = MediaTheme.colors.text
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                items(gallery) { item ->
                    Box(
                        modifier = Modifier
                            .clickable {
                                onNavigateToPhoto(item.path)
                            }
                    ) {
                        var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }

                        LaunchedEffect(item.path) {
                            withContext(Dispatchers.IO) {
                                val bitmap = loadBitmapFromStorage(item.path)
                                withContext(Dispatchers.Main) {
                                    imageBitmap = bitmap
                                }
                            }
                        }

                        imageBitmap?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier.size(180.dp))
                        }
                    }
                }

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
                            color = MediaTheme.colors.text)
                    }
                }
            }
        }
    }
}
