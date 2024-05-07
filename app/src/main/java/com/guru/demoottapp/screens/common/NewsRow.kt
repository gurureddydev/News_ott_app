package com.guru.demoottapp.screens.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.material3.Border
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardLayoutDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.ImmersiveListScope
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.StandardCardLayout
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.guru.demoottapp.screens.home.News
import com.guru.demoottapp.ui.theme.NewsBorderWidth
import com.guru.demoottapp.ui.theme.NewsCardShape
import com.guru.demoottapp.ui.theme.utils.createInitialFocusRestorerModifiers
import com.guru.demoottapp.ui.theme.utils.handleDPadKeyEvents
import com.guru.demoottapp.ui.theme.utils.ifElse
import com.guru.demoottapp.ui.theme.utils.rememberChildPadding
import com.guru.demoottapp.util.StringConstants
import com.guru.demoottapp.util.showToast

enum class ItemDirection(val aspectRatio: Float) {
    Vertical(10.5f / 16f),
    Horizontal(16f / 9f);
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun NewsRow(
    modifier: Modifier = Modifier,
    itemDirection: ItemDirection = ItemDirection.Vertical,
    startPadding: Dp = rememberChildPadding().start,
    endPadding: Dp = rememberChildPadding().end,
    title: String,
    titleStyle: TextStyle = MaterialTheme.typography.headlineLarge.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp
    ),
    goToVideoPlayer: (news: News) -> Unit,
    showIndexOverImage: Boolean = false,
    focusedItemIndex: (index: Int) -> Unit = {},
    news: List<News>,
    onMovieClick: (news: News) -> Unit = {}
) {
    Column(
        modifier = modifier.focusGroup()
    ) {
        Text(
            text = title,
            style = titleStyle,
            color = Color.White,
            modifier = Modifier
                .alpha(1f)
                .padding(start = startPadding)
                .padding(vertical = 16.dp)
        )

        AnimatedContent(
            targetState = news,
            label = "",
        ) { movieState ->
            val focusRestorerModifiers = createInitialFocusRestorerModifiers()

            TvLazyRow(
                modifier = Modifier
                    .then(focusRestorerModifiers.parentModifier),
                pivotOffsets = PivotOffsets(parentFraction = 0.07f),
                contentPadding = PaddingValues(
                    start = startPadding,
                    end = endPadding,
                ),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                itemsIndexed(movieState, key = { _, movie -> movie.id }) { index, movie ->
                    NewsRowItem(
                        modifier = Modifier
                            .ifElse(
                                index == 0,
                                focusRestorerModifiers.childModifier
                            )
                            .weight(1f)
                            .handleDPadKeyEvents(onEnter = { goToVideoPlayer(movie) }),
                        focusedItemIndex = focusedItemIndex,
                        index = index,
                        itemDirection = itemDirection,
                        onMovieClick = onMovieClick,
                        news = movie,
                        goToVideoPlayer = goToVideoPlayer,
                        showIndexOverImage = showIndexOverImage
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalTvMaterial3Api::class)
@Composable
fun ImmersiveListScope.ImmersiveListNewsRow(
    modifier: Modifier = Modifier,
    startPadding: Dp = rememberChildPadding().start,
    endPadding: Dp = rememberChildPadding().end,
    goToVideoPlayer: (news: News) -> Unit,
    itemDirection: ItemDirection = ItemDirection.Horizontal,
    title: String? = null,
    titleStyle: TextStyle = MaterialTheme.typography.headlineLarge.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp
    ),
    showIndexOverImage: Boolean = false,
    focusedItemIndex: (index: Int) -> Unit = {},
    news: List<News>,
    onMovieClick: (news: News) -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier.focusGroup()
    ) {
        if (title != null) {
            Text(
                text = title,
                style = titleStyle,
                color = Color.White,
                modifier = Modifier
                    .alpha(1f)
                    .padding(start = startPadding)
                    .padding(vertical = 16.dp)
            )
        }

        AnimatedContent(
            targetState = news,
            label = "",
        ) { movieState ->
            TvLazyRow(
                modifier = Modifier.focusRestorer(),
                pivotOffsets = PivotOffsets(parentFraction = 0.07f)
            ) {
                item {
                    Spacer(modifier = Modifier.padding(start = startPadding))
                    Box(modifier = Modifier.focusRequester(focusRequester))
                }

                movieState.forEachIndexed { index, movie ->
                    item {
                        key(movie.id) {
                            NewsRowItem(
                                modifier = Modifier
                                    .weight(1f)
                                    .immersiveListItem(index),
                                focusedItemIndex = focusedItemIndex,
                                index = index,
                                itemDirection = itemDirection,
                                onMovieClick = onMovieClick,
                                news = movie,
                                goToVideoPlayer = goToVideoPlayer,
                                showIndexOverImage = showIndexOverImage
                            )
                        }
                    }
                    item { Spacer(modifier = Modifier.padding(end = 20.dp)) }
                }

                item { Spacer(modifier = Modifier.padding(start = endPadding)) }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalComposeUiApi::class, ExperimentalTvMaterial3Api::class)
private fun NewsRowItem(
    modifier: Modifier = Modifier,
    focusedItemIndex: (index: Int) -> Unit,
    index: Int,
    goToVideoPlayer: (news: News) -> Unit,
    itemDirection: ItemDirection,
    onMovieClick: (news: News) -> Unit,
    news: News,
    showIndexOverImage: Boolean
) {
    var isItemFocused by remember { mutableStateOf(false) }
    val context = LocalContext.current

    StandardCardLayout(
        modifier = Modifier
            .onFocusChanged {
                isItemFocused = it.isFocused
                if (isItemFocused) {
                    focusedItemIndex(index)
                }
            }
            .focusProperties {
                if (index == 0) {
                    left = FocusRequester.Cancel
                }
            }
            .then(modifier),
        title = {
            NewsRowItemText(
                news = news
            )
        },
        imageCard = {
            CardLayoutDefaults.ImageCard(
                onClick = {
                    context.showToast("Watch news")
                    onMovieClick(news)
                },
                shape = CardDefaults.shape(NewsCardShape),
                border = CardDefaults.border(
                    focusedBorder = Border(
                        border = BorderStroke(
                            width = NewsBorderWidth,
                            color = Color.White
                        ),
                        shape = NewsCardShape
                    )
                ),
                scale = CardDefaults.scale(focusedScale = 1f),
                interactionSource = it
            ) {
                NewsRowItemImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(itemDirection.aspectRatio),
                    showIndexOverImage = showIndexOverImage,
                    news = news,
                    index = index,
                    itemDirection = ItemDirection.Horizontal
                )
            }
        },
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun NewsRowItemImage(
    showIndexOverImage: Boolean,
    news: News,
    index: Int,
    itemDirection: ItemDirection,
    modifier: Modifier = Modifier,
) {
    Box(contentAlignment = Alignment.BottomStart) {
        val imageSize = when (itemDirection) {
            ItemDirection.Vertical -> Pair(150.dp, 250.dp)
            ItemDirection.Horizontal -> Pair(250.dp, 150.dp)
        }
        AsyncImage(
            modifier = modifier
                .width(imageSize.first)
                .height(imageSize.second)
                .drawWithContent {
                    drawContent()
                    if (showIndexOverImage) {
                        drawRect(
                            color = Color.Black.copy(
                                alpha = 0.1f
                            )
                        )
                    }
                },
            model = ImageRequest.Builder(LocalContext.current)
                .crossfade(true)
                .data(news.posterUri)
                .build(),
            contentDescription = "movie poster of ${news.name}",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .padding(6.dp)
                .align(Alignment.BottomEnd)
                .background(color = Color.Black)
        ) {
            Text(
                modifier = Modifier.padding(3.dp),
                text = "3:06",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
        }
        if (showIndexOverImage) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "#${index.inc()}",
                style = MaterialTheme.typography.displayLarge
                    .copy(
                        shadow = Shadow(
                            offset = Offset(0.5f, 0.5f),
                            blurRadius = 5f
                        ),
                        color = Color.White
                    ),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun NewsRowItemText(
    news: News,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = StringConstants.Composable.truncateText(news.name, 3),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            color = Color.White,
            modifier = Modifier.padding(top = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(
                text = "4K HD",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "12k Views",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "3 min ago",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White,
                textAlign = TextAlign.End, // Align text to the end (right)
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
