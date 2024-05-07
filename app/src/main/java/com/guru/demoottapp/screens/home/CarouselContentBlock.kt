package com.guru.demoottapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.guru.demoottapp.R
import com.guru.demoottapp.ui.theme.NewsButtonShape

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
            text = item.name, style = MaterialTheme.typography.displaySmall.copy(
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.5f),
                    offset = Offset(x = 2f, y = 4f),
                    blurRadius = 2f
                )
            ), maxLines = 1
        )

        Text(
            text = item.description, style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.surface.copy(
                    alpha = 0.65f
                ), shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.5f),
                    offset = Offset(x = 2f, y = 4f),
                    blurRadius = 2f
                )
            ), maxLines = 1, modifier = Modifier.padding(top = 8.dp)
        )

        /* Action button to play content */
//        Button(
//            onClick = { goToVideoPlayer(item) },
//            modifier = Modifier.padding(top = 20.dp),
//            colors = ButtonDefaults.buttonColors(
//                contentColor = Color.Black,
//                containerColor = Color.White
//            )
//        ) {
//            Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "")
//            Text(
//                text = stringResource(R.string.watch_now),
//                style = MaterialTheme.typography.titleSmall
//            )
        WatchNowButton(item = item, goToVideoPlayer = { goToVideoPlayer(item) })
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun WatchNowButton(item: News, goToVideoPlayer: (news: News) -> Unit) {
    androidx.tv.material3.Button(
        onClick = { goToVideoPlayer(item) },
        modifier = Modifier.padding(top = 8.dp),
        contentPadding = androidx.tv.material3.ButtonDefaults.ButtonWithIconContentPadding,
        shape = androidx.tv.material3.ButtonDefaults.shape(shape = NewsButtonShape),
        colors = androidx.tv.material3.ButtonDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            focusedContentColor = MaterialTheme.colorScheme.background,
        ),
        scale = androidx.tv.material3.ButtonDefaults.scale(scale = 1f)
    ) {
        androidx.tv.material3.Icon(
            imageVector = Icons.Outlined.PlayArrow,
            contentDescription = null,
        )
        Spacer(Modifier.size(8.dp))
        Text(
            text = stringResource(R.string.watch_now), style = MaterialTheme.typography.titleSmall
        )
    }
}
