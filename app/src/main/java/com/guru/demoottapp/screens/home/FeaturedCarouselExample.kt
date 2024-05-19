package com.guru.demoottapp.screens.home

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.rememberCarouselState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.guru.demoottapp.R
import com.guru.demoottapp.ui.theme.utils.Padding

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
            posterUri = R.drawable.news_i1
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
            posterUri = R.drawable.news_i3
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
            posterUri = R.drawable.news_i2
        )
    )
}

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun FeaturedCarouselExample(
    goToVideoPlayer: (news: News) -> Unit,
    padding: Padding,
) {

    val list = generateDummyMovies()
    val carouselState = rememberCarouselState()
    var isCarouselFocused by remember { mutableStateOf(false) }

    Carousel(
        itemCount = list.size,
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .padding(start = padding.start, end = padding.start, top = padding.top)
            .clip(ShapeDefaults.Medium)
            .border(
                width = 4.dp,
                color = MaterialTheme.colorScheme.surface.copy(alpha = if (isCarouselFocused) 1f else 0f),
                shape = ShapeDefaults.Medium,
            )
            .onFocusChanged {
                isCarouselFocused = it.hasFocus
            },
        carouselState = carouselState,
        carouselIndicator = {
            CarouselDefaults.IndicatorRow(
                itemCount = list.size,
                activeItemIndex = carouselState.activeItemIndex,
                modifier = Modifier
                    .align(Alignment.BottomEnd) //Bottom indicator alignment
                    .padding(vertical = 16.dp, horizontal = 30.dp),
            )
        },
        contentTransformEndToStart =
        fadeIn(tween(1000)).togetherWith(fadeOut(tween(1000))),
        contentTransformStartToEnd =
        fadeIn(tween(1000)).togetherWith(fadeOut(tween(1000)))
    ) { itemIndex ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            /* Showing background image */
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(list[itemIndex].posterUri)
                    .build(),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.Center
            )
            CarouselContentBlock(
                item = list[itemIndex],
                goToVideoPlayer = goToVideoPlayer,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 60.dp, end = 30.dp, bottom = 30.dp)
                    .fillMaxWidth(0.6f)
                    .animateEnterExit(
                        enter = slideInHorizontally(animationSpec = tween(1000)) { it / 2 },
                        exit = slideOutHorizontally(animationSpec = tween(1000))
                    )
            )

        }
    }
}
