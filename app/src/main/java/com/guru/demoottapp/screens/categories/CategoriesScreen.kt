package com.guru.demoottapp.screens.categories

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.itemsIndexed
import androidx.tv.foundation.lazy.grid.rememberTvLazyGridState
import androidx.tv.material3.Border
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardLayoutDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.StandardCardLayout
import androidx.tv.material3.Text
import com.guru.demoottapp.ui.theme.NewsBorderWidth
import com.guru.demoottapp.ui.theme.NewsCardShape
import com.guru.demoottapp.ui.theme.utils.GradientBg
import com.guru.demoottapp.ui.theme.utils.rememberChildPadding

// Define your data model for movie categories
data class NewsCategory(
    val id: String,
    val name: String
)

// Dummy implementation of CategoriesScreenUiState
sealed class CategoriesScreenUiState {
    object Loading : CategoriesScreenUiState()
    data class Ready(val categoryList: List<NewsCategory>) : CategoriesScreenUiState()
}

var dummyNewsCategories = listOf(
    NewsCategory("1", "Politics"),
    NewsCategory("2", "Business"),
    NewsCategory("3", "Technology"),
    NewsCategory("4", "Entertainment"),
    NewsCategory("5", "Science"),
    NewsCategory("6", "Health"),
    NewsCategory("7", "Sports"),
    NewsCategory("8", "World"),
    NewsCategory("9", "Environment"),
    NewsCategory("10", "Education"),
    NewsCategory("11", "Travel"),
    NewsCategory("12", "Fashion"),
)

// Dummy implementation of uiState
val uiState: CategoriesScreenUiState = CategoriesScreenUiState.Ready(dummyNewsCategories)

@Composable
fun CategoriesScreen(
    gridColumns: Int = 4,
    onCategoryClick: (categoryId: String) -> Unit,
    onScroll: (isTopBarVisible: Boolean) -> Unit,
) {
    when (val s = uiState) {
        is CategoriesScreenUiState.Loading -> {
            Loading()
        }
        is CategoriesScreenUiState.Ready -> {
            Catalog(
                gridColumns = gridColumns,
                movieCategories = s.categoryList,
                onCategoryClick = onCategoryClick,
                onScroll = onScroll,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalTvMaterial3Api::class)
@Composable
private fun Catalog(
    movieCategories: List<NewsCategory>,
    modifier: Modifier = Modifier,
    gridColumns: Int = 4,
    onCategoryClick: (categoryId: String) -> Unit,
    onScroll: (isTopBarVisible: Boolean) -> Unit,
) {
    val childPadding = rememberChildPadding()
    val tvLazyGridState = rememberTvLazyGridState()
    val shouldShowTopBar by remember {
        derivedStateOf {
            tvLazyGridState.firstVisibleItemIndex == 0 &&
                    tvLazyGridState.firstVisibleItemScrollOffset < 100
        }
    }
    LaunchedEffect(shouldShowTopBar) {
        onScroll(shouldShowTopBar)
    }

    AnimatedContent(
        targetState = movieCategories,
        modifier = Modifier
            .padding(horizontal = childPadding.start)
            .padding(top = childPadding.top),
        label = "",
    ) { it ->
        TvLazyVerticalGrid(
            state = tvLazyGridState,
            modifier = modifier,
            columns = TvGridCells.Fixed(gridColumns),
            content = {
                itemsIndexed(it) { index, movieCategory ->
                    var isFocused by remember { mutableStateOf(false) }
                    StandardCardLayout(
                        imageCard = {
                            CardLayoutDefaults.ImageCard(
                                shape = CardDefaults.shape(shape = NewsCardShape),
                                border = CardDefaults.border(
                                    focusedBorder = Border(
                                        border = BorderStroke(
                                            width = NewsBorderWidth,
                                            color = MaterialTheme.colorScheme.onSurface
                                        ),
                                        shape = NewsCardShape
                                    ),
                                    pressedBorder = Border(
                                        border = BorderStroke(
                                            width = NewsBorderWidth,
                                            color = MaterialTheme.colorScheme.border
                                        ),
                                        shape = NewsCardShape
                                    )
                                ),
                                scale = CardDefaults.scale(focusedScale = 1f),
                                onClick = { onCategoryClick(movieCategory.id) },
                                interactionSource = it
                            ) {
                                val itemAlpha by animateFloatAsState(
                                    targetValue = if (isFocused) .6f else 0.2f,
                                    label = ""
                                )
                                val textColor = if (isFocused) Color.Black else Color.Black

                                Box(contentAlignment = Alignment.Center) {
                                    Box(modifier = Modifier.alpha(itemAlpha)) {
                                        GradientBg()
                                    }
                                    Text(
                                        text = movieCategory.name,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            color = textColor,
                                        )
                                    )
                                }
                            }
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .aspectRatio(16 / 9f)
                            .onFocusChanged {
                                isFocused = it.isFocused || it.hasFocus
                            }
                            .focusProperties {
                                if (index % gridColumns == 0) {
                                    left = FocusRequester.Cancel
                                }
                            },
                        title = {}
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Text(text = "Loading...", modifier = modifier)
}