package com.example.taskplayer.screen.MainScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.taskplayer.component.MainButton
import com.example.taskplayer.data.remote.auth.QuotesResponse
import com.example.taskplayer.ui.theme.BlockButton
import com.example.taskplayer.ui.theme.MediaTheme

@Composable
fun QuotesItem(quotes: QuotesResponse) {
    Box(
        modifier = Modifier
            .width(339.dp)
            .height(170.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(16.dp)

    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        )
        {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(modifier = Modifier.weight(1f)) {

                    Text(
                        text = quotes.title,
                        style = MediaTheme.typography.alegreya25,

                        )

                    Text(
                        text = quotes.description,
                        style = MediaTheme.typography.alegreyaSans15
                    )
                }
                AsyncImage(
                    model = quotes.image,
                    contentDescription = quotes.title,
                    modifier = Modifier
                        .width(166.dp)
                        .height(111.dp)
                )
            }


            MainButton(
                onClick = {},
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.Start)
            ) {
                Text(
                    text = "подробнее",
                    style = MediaTheme.typography.alegreyaSans15,
                    color = MediaTheme.colors.text
                )
            }

        }
    }
}