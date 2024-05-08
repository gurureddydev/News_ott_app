package com.guru.demoottapp.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import com.guru.demoottapp.screens.common.NewsRow
import com.guru.demoottapp.ui.theme.utils.rememberChildPadding
import com.guru.demoottapp.util.StringConstants
import com.guru.demoottapp.util.showToast

@Composable
fun HomeScreen(
    onScroll: (isTopBarVisible: Boolean) -> Unit,
    goToVideoPlayer: (news: News) -> Unit,
    isTopBarVisible: Boolean,
) {
    Catalog(
        modifier = Modifier.fillMaxSize(),
        onScroll = onScroll,
        isTopBarVisible = isTopBarVisible,
        goToVideoPlayer = goToVideoPlayer,
    )
}

@Composable
private fun Catalog(
    modifier: Modifier = Modifier,
    onScroll: (isTopBarVisible: Boolean) -> Unit,
    goToVideoPlayer: (news: News) -> Unit,
    isTopBarVisible: Boolean = true,
) {
    val context = LocalContext.current
    val tvLazyListState = rememberTvLazyListState()
    var immersiveListHasFocus by remember { mutableStateOf(false) }
    val childPadding = rememberChildPadding()
    val pivotOffset = remember { PivotOffsets() }
    val pivotOffsetForImmersiveList = remember { PivotOffsets(0f, 0f) }
    val shouldShowTopBar by remember {
        derivedStateOf {
            tvLazyListState.firstVisibleItemIndex == 0 && tvLazyListState.firstVisibleItemScrollOffset < 300
        }
    }

    LaunchedEffect(shouldShowTopBar) {
        onScroll(shouldShowTopBar)
    }
    LaunchedEffect(isTopBarVisible) {
        if (isTopBarVisible) tvLazyListState.animateScrollToItem(0)
    }
    TvLazyColumn(
        modifier = modifier,
        state = tvLazyListState,
        pivotOffsets = if (immersiveListHasFocus) pivotOffsetForImmersiveList else pivotOffset,
        contentPadding = PaddingValues(bottom = 18.dp)
    ) {
        item {
            FeaturedCarouselExample(
                goToVideoPlayer = goToVideoPlayer, padding = childPadding,
            )
        }
        item(contentType = "Top10MoviesList") {
            Top10MoviesList(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .padding(top = 16.dp)
                    .onFocusChanged {
                        immersiveListHasFocus = it.hasFocus
                    }, newsState = generateDummyMovies(), goToVideoPlayer = goToVideoPlayer
            )
        }
        item(contentType = "MoviesRow") {
            NewsRow(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .padding(top = 16.dp),
                news = generateDummyMovies(),
                title = StringConstants.Composable.HomeScreenTrendingTitle,
                goToVideoPlayer = goToVideoPlayer
            )
        }
        item(contentType = "MoviesRow") {
            NewsRow(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .padding(top = 16.dp),
                news = generateDummyMovies(),
                title = StringConstants.Composable.HomeScreenNowPlayingMoviesTitle,
                goToVideoPlayer = goToVideoPlayer
            )
        }
    }
}
