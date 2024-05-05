package com.guru.demoottapp.screens.categories

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.material3.Border
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardLayoutDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.StandardCardLayout
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.guru.demoottapp.R
import com.guru.demoottapp.screens.home.News
import com.guru.demoottapp.ui.theme.NewsBorderWidth
import com.guru.demoottapp.ui.theme.NewsBottomListPadding
import com.guru.demoottapp.ui.theme.NewsCardShape
import com.guru.demoottapp.ui.theme.utils.focusOnInitialVisibility
import com.guru.demoottapp.ui.theme.utils.rememberChildPadding
import com.guru.demoottapp.util.StringConstants

object CategoryNewsListScreen {
    const val CategoryIdBundleKey = "categoryId"
}

data class NewsCategoryDetails(
    val id: Long,
    val name: String,
    val news: List<News>,
)


@Composable
fun CategoryNewsListScreen(
    onBackPressed: () -> Unit,
    onMovieSelected: (News) -> Unit,
) {

    // Dummy data for testing
    val dummyCategoryDetails = NewsCategoryDetails(
        id = 0,
        name = "Political",
        news = listOf(
            News(
                id = 0,
                name = "Dummy News 1",
                description = "Description for Dummy News 1",
                posterUri = R.drawable.v1
            ),
            News(
                id = 1,
                name = "Dummy News 2",
                description = "Description for Dummy News 2",
                posterUri = R.drawable.v2
            ),
            News(
                id = 0,
                name = "Dummy News 1",
                description = "Description for Dummy News 1",
                posterUri = R.drawable.v3
            ),
            News(
                id = 1,
                name = "Dummy News 2",
                description = "Description for Dummy News 2",
                posterUri = R.drawable.v4
            ),
            News(
                id = 0,
                name = "Dummy News 1",
                description = "Description for Dummy News 1",
                posterUri = R.drawable.v5
            ),
            News(
                id = 1,
                name = "Dummy News 2",
                description = "Description for Dummy News 2",
                posterUri = R.drawable.v6
            ),
            News(
                id = 0,
                name = "Dummy News 1",
                description = "Description for Dummy News 1",
                posterUri = R.drawable.v7
            ),
            News(
                id = 1,
                name = "Dummy News 2",
                description = "Description for Dummy News 2",
                posterUri = R.drawable.v1
            ),
            News(
                id = 1,
                name = "Dummy News 2",
                description = "Description for Dummy News 2",
                posterUri = R.drawable.v6
            ),
            News(
                id = 0,
                name = "Dummy News 1",
                description = "Description for Dummy News 1",
                posterUri = R.drawable.v7
            ),
            News(
                id = 1,
                name = "Dummy News 2",
                description = "Description for Dummy News 2",
                posterUri = R.drawable.v1
            ),
            News(
                id = 0,
                name = "Dummy News 1",
                description = "Description for Dummy News 1",
                posterUri = R.drawable.v1
            ),
        )
    )
    CategoryDetails(
        categoryDetails = dummyCategoryDetails,
        onBackPressed = onBackPressed,
        onMovieSelected = onMovieSelected
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun CategoryDetails(
    categoryDetails: NewsCategoryDetails,
    onBackPressed: () -> Unit,
    onMovieSelected: (News) -> Unit,
    modifier: Modifier = Modifier
) {
    val childPadding = rememberChildPadding()
    val isFirstItemVisible = remember { mutableStateOf(false) }

    BackHandler(onBack = onBackPressed)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = categoryDetails.name,
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(
                vertical = childPadding.top.times(3.5f)
            )
        )
        TvLazyVerticalGrid(
            columns = TvGridCells.Fixed(6)
        ) {
            categoryDetails.news.forEachIndexed { index, news ->
                item {
                    key(news.id) {
                        StandardCardLayout(
                            modifier = Modifier
                                .aspectRatio(1 / 1.5f)
                                .padding(8.dp)
                                .then(
                                    if (index == 0)
                                        Modifier.focusOnInitialVisibility(isFirstItemVisible)
                                    else Modifier
                                ),
                            imageCard = {
                                CardLayoutDefaults.ImageCard(
                                    shape = CardDefaults.shape(shape = NewsCardShape),
                                    border = CardDefaults.border(
                                        focusedBorder = Border(
                                            border = BorderStroke(
                                                width = NewsBorderWidth,
                                                color = Color.White
                                            ),
                                            shape = NewsCardShape
                                        ),
                                        pressedBorder = Border(
                                            border = BorderStroke(
                                                width = NewsBorderWidth,
                                                color = MaterialTheme.colorScheme.border
                                            ),
                                            shape = NewsCardShape
                                        ),
                                    ),
                                    scale = CardDefaults.scale(focusedScale = 1f),
                                    onClick = { onMovieSelected(news) },
                                    interactionSource = it
                                ) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(news.posterUri)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = StringConstants
                                            .Composable
                                            .ContentDescription
                                            .moviePoster(news.name),
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            },
                            title = {},
                        )
                    }
                }
            }
            item(span = { TvGridItemSpan(currentLineSpan = 6) }) {
                Spacer(modifier = Modifier.padding(bottom = NewsBottomListPadding))
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Text(text = "Loading...", modifier = modifier)
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun Error(modifier: Modifier = Modifier) {
    Text(text = "Oops, something went wrong...", modifier = modifier)
}

@Preview
@Composable
fun CategoryNewsListScreenPreview() {
    CategoryNewsListScreen(
        onBackPressed = {},
        onMovieSelected = {}
    )

}

@Preview
@Composable
fun CategoryDetailsPreview() {
    CategoryDetails(
        categoryDetails = NewsCategoryDetails(
            id = 0,
            name = "Test Category",
            news = listOf(
                News(
                    id = 0,
                    name = "Dummy News 1",
                    description = "Description for Dummy News 1",
                    posterUri = R.drawable.news3
                ),
                News(
                    id = 1,
                    name = "Dummy News 2",
                    description = "Description for Dummy News 2",
                    posterUri = R.drawable.news3
                )
            )
        ),
        onBackPressed = {},
        onMovieSelected = {}
    )
}
