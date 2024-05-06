package com.guru.demoottapp.screens.home


import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselDefaults
import androidx.tv.material3.CarouselState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.guru.demoottapp.util.StringConstants
import com.guru.demoottapp.R
import com.guru.demoottapp.ui.theme.utils.Padding
import com.guru.demoottapp.ui.theme.NewsBorderWidth
import com.guru.demoottapp.ui.theme.NewsButtonShape
import com.guru.demoottapp.ui.theme.utils.handleDPadKeyEvents

// Dummy data models
data class News(
    val id: Long,
    val name: String,
    val videoUri: Uri,
    val description: String,
    val posterUri: Int
)

// Function to generate dummy movie data
fun generateDummyMovies(): List<News> {
    return listOf(
        News(
            id = 0,
            name = "Breaking News: Earthquake Hits Major City",
            videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"),
            description = "A powerful earthquake shook the city early this morning, causing widespread damage and panic.",
            posterUri = R.drawable.news
        ), News(
            id = 1,
            name = "Political Scandal Uncovered",
            videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"),
            description = "Investigative journalists reveal shocking details about corruption at the highest levels of government.",
            posterUri = R.drawable.news1
        ), News(
            id = 2,
            name = "Climate Change Report: Urgent Action Needed",
            videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"),
            description = "New scientific findings show that the effects of climate change are accelerating, prompting calls for immediate intervention.",
            posterUri = R.drawable.news2
        ), News(
            id = 3,
            name = "Health Crisis: New Virus Strain Detected",
            videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"),
            description = "Health officials warn about a new, highly contagious virus strain spreading rapidly across regions.",
            posterUri = R.drawable.news3
        ), News(
            id = 4,
            name = "Technology Breakthrough: AI Revolutionizes Industry",
            videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"),
            description = "Experts discuss the transformative impact of artificial intelligence on various sectors of the economy.",
            posterUri = R.drawable.news4
        ), News(
            id = 5,
            name = "Global Economy Update: Market Volatility Continues",
            videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"),
            description = "Financial analysts analyze the latest trends in global markets amid ongoing volatility and uncertainty.",
            posterUri = R.drawable.news3
        ), News(
            id = 6,
            name = "Space Exploration: New Discoveries Beyond Our Solar System",
            videoUri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"),
            description = "Astronomers announce groundbreaking discoveries of exoplanets and potential signs of extraterrestrial life.",
            posterUri = R.drawable.news2
        )
    )
}

// Custom Saver for CarouselState
@OptIn(ExperimentalTvMaterial3Api::class)
val CarouselSaver =
    Saver<CarouselState, Int>(save = { it.activeItemIndex }, restore = { CarouselState(it) })

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun FeaturedNewsCarousel(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    goToVideoPlayer: (news: News) -> Unit,
    padding: Padding,
) {
    val movies = generateDummyMovies()
    val carouselState = rememberSaveable(saver = CarouselSaver) { CarouselState(0) }
    var isCarouselFocused by rememberSaveable { mutableStateOf(false) }

    Carousel(modifier = modifier
        .padding(
            start = padding.start, end = padding.start, top = padding.top
        )
        .border(
            width = NewsBorderWidth,
            color = Color.White.copy(alpha = if (isCarouselFocused) 1f else 0f),
            shape = ShapeDefaults.Medium,
        )
        .semantics {
            contentDescription = StringConstants.Composable.ContentDescription.MoviesCarousel
        }

        .clip(ShapeDefaults.Medium)
        .onFocusChanged {
            isCarouselFocused = it.hasFocus
        }
        .handleDPadKeyEvents(onEnter = {
            goToVideoPlayer(movies[carouselState.activeItemIndex])
        }),
        itemCount = movies.size,
        carouselState = carouselState,
        carouselIndicator = {
            CarouselIndicator(
                itemCount = movies.size, activeItemIndex = carouselState.activeItemIndex
            )
        },
        contentTransformStartToEnd = fadeIn(tween(durationMillis = 1000)).togetherWith(
            fadeOut(
                tween(durationMillis = 1000)
            )
        ),
        contentTransformEndToStart = fadeIn(tween(durationMillis = 1000)).togetherWith(
            fadeOut(
                tween(durationMillis = 1000)
            )
        ),
        content = { index ->
            val movie = movies[index]
            // background
            CarouselItemBackground(
                news = movie, onClick = onClick, modifier = Modifier
                    .fillMaxSize()
                    .focusable(true)
            )
            // foreground
            CarouselItemForeground(
                news = movie,
                isCarouselFocused = isCarouselFocused,
                modifier = Modifier
                    .fillMaxSize()
                    .focusable(true)
            ) {
//                goToVideoPlayer(movie)
            }
        }

    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun BoxScope.CarouselIndicator(
    itemCount: Int, activeItemIndex: Int, modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .padding(32.dp)
        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
        .graphicsLayer {
            clip = true
            shape = ShapeDefaults.ExtraSmall
        }
        .align(Alignment.BottomEnd)) {
        CarouselDefaults.IndicatorRow(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            itemCount = itemCount,
            activeItemIndex = activeItemIndex,
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CarouselItemForeground(
    news: News,
    modifier: Modifier = Modifier,
    isCarouselFocused: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.BottomStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = news.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium.copy(
                ),
                maxLines = 1
            )
            Text(
                text = news.description,
                color = Color.White.copy(alpha = 0.5f),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.65f
                    ),
                ),
                maxLines = 1,
                modifier = Modifier.padding(top = 8.dp)
            )
            AnimatedVisibility(visible = isCarouselFocused, content = {
                WatchNowButton(onClick = onClick)
            })
        }
    }
}

@Composable
private fun CarouselItemBackground(
    news: News, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    AsyncImage(model = news.posterUri,
        contentDescription = StringConstants.Composable.ContentDescription.moviePoster(news.name),
        modifier = modifier
            .clickable { onClick() }
            .drawWithContent {
                drawContent()
                drawRect(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent, Color.Black.copy(alpha = 0.5f)
                        )
                    )
                )
            },
        contentScale = ContentScale.Crop
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun WatchNowButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(top = 8.dp),
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        shape = ButtonDefaults.shape(shape = NewsButtonShape),
        colors = ButtonDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            focusedContentColor = MaterialTheme.colorScheme.surface,
        ),
        scale = ButtonDefaults.scale(scale = 1f)
    ) {
        Icon(
            imageVector = Icons.Outlined.PlayArrow,
            contentDescription = stringResource(com.guru.demoottapp.R.string.watch_now)
        )
        Spacer(Modifier.size(8.dp))
        Text(
            text = stringResource(com.guru.demoottapp.R.string.watch_now),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
