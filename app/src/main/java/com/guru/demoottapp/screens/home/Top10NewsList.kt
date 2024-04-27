package com.guru.demoottapp.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.ImmersiveList
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.guru.demoottapp.R
import com.guru.demoottapp.screens.common.ImmersiveListNewsRow
import com.guru.demoottapp.screens.common.ItemDirection
import com.guru.demoottapp.ui.theme.utils.rememberChildPadding

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun Top10MoviesList(
    modifier: Modifier = Modifier,
    newsState: List<News>,
) {
    var currentItemIndex by remember { mutableIntStateOf(0) }
    var isListFocused by remember { mutableStateOf(false) }

    ImmersiveList(
        modifier = modifier,
        background = { _, listHasFocus ->
            isListFocused = listHasFocus
            val gradientColor = MaterialTheme.colorScheme.onBackground
            AnimatedVisibility(
                visible = isListFocused,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
                modifier = Modifier
                    .height(432.dp)
                    .gradientOverlay(gradientColor)
            ) {
                val news = remember(newsState, currentItemIndex) {
                    newsState[currentItemIndex]
                }

                Crossfade(
                    targetState = news.posterUri,
                    label = "posterUriCrossFade"
                ) { posterUri ->
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(432.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(posterUri)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        },
        list = {
            Column {
                if (isListFocused) {
                    val movie = remember(newsState, currentItemIndex) {
                        newsState[currentItemIndex]
                    }
                    Column(
                        modifier = Modifier.padding(
                            start = rememberChildPadding().start,
                            bottom = 32.dp
                        )
                    ) {
                        Text(
                            text = movie.name,
                            style = MaterialTheme.typography.displaySmall,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.padding(top = 8.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(0.5f),
                            text = movie.description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
                ImmersiveListNewsRow(
                    itemDirection = ItemDirection.Horizontal,
                    news = newsState,
                    title = if (isListFocused) null
                    else stringResource(R.string.top_10_movies_title),
                    showIndexOverImage = true,
                    focusedItemIndex = { focusedIndex -> currentItemIndex = focusedIndex },
                )
            }
        }
    )
}

fun Modifier.gradientOverlay(gradientColor: Color) = this.then(drawWithCache {
    val horizontalGradient = Brush.horizontalGradient(
        colors = listOf(
            gradientColor,
            Color.Transparent
        ),
        startX = size.width.times(0.2f),
        endX = size.width.times(0.7f)
    )
    val verticalGradient = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            gradientColor
        ),
        endY = size.width.times(0.3f)
    )
    val linearGradient = Brush.linearGradient(
        colors = listOf(
            gradientColor,
            Color.Transparent
        ),
        start = Offset(
            size.width.times(0.2f),
            size.height.times(0.5f)
        ),
        end = Offset(
            size.width.times(0.9f),
            0f
        )
    )

    onDrawWithContent {
        drawContent()
        drawRect(horizontalGradient)
        drawRect(verticalGradient)
        drawRect(linearGradient)
    }
})
