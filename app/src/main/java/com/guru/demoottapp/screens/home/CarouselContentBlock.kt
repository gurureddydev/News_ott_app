package com.guru.demoottapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CarouselContentBlock(
    item: News, modifier: Modifier, goToVideoPlayer: (news: News) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        /* About content */
//        Text(
//            text = "${item.category} • ${item.getYear()} • ${item.getHours()}",
//            style = AppTypography.labelMedium.copy(color = bodyColor),
//        )

        /* Title */
        Text(
            text = item.name,
            style = MaterialTheme.typography.headlineSmall.copy(
            ),
            modifier = Modifier.padding(top = 4.dp)
        )

        /* Description*/
        Text(
            text = item.description,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.surface.copy(
                    alpha = 0.65f
                ),
            ),
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 2,
            softWrap = true,
            overflow = TextOverflow.Ellipsis
        )

        /* Action button to play content */
        Button(
            onClick = { goToVideoPlayer(item) },
            modifier = Modifier.padding(top = 20.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black
            )
        ) {
            Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "")
            Text(text = "Watch Now")
        }
    }
}
