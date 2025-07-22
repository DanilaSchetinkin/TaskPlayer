package com.example.taskplayer.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun BottomBar(
    currentRoute: String?,
    onItemClick: (String) -> Unit
){
    Row(horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()) {

        bottomNavItem.forEach { item ->
            val isSelected = item.route == currentRoute
            val icon = if (isSelected) item.selectedIcon else item.unselectedIcon

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .clickable(
                    onClick = { onItemClick(item.route) }
                )){
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = item.route,
                )
            }
        }
    }
}