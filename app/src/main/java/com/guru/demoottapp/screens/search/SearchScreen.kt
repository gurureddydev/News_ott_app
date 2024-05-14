package com.guru.demoottapp.screens.search

import android.view.KeyEvent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyListState
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.Border
import androidx.tv.material3.ClickableSurfaceDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.guru.demoottapp.R
import com.guru.demoottapp.screens.common.NewsRow
import com.guru.demoottapp.screens.home.News
import com.guru.demoottapp.screens.home.generateDummyMovies
import com.guru.demoottapp.ui.theme.NewsCardShape
import com.guru.demoottapp.ui.theme.utils.rememberChildPadding

@OptIn(ExperimentalComposeUiApi::class, ExperimentalTvMaterial3Api::class)
@Composable
fun SearchResult(
    modifier: Modifier = Modifier,
    goToVideoPlayer: (news: News) -> Unit,
    allNews: List<News>
) {
    val tvLazyColumnState: TvLazyListState = rememberTvLazyListState()

    val childPadding = rememberChildPadding()
    var searchQuery by remember { mutableStateOf("") }
    val tfFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val tfInteractionSource = remember { MutableInteractionSource() }
    val isTfFocused by tfInteractionSource.collectIsFocusedAsState()

    val filteredNews = remember(searchQuery) {
        derivedStateOf {
            allNews.filter { news ->
                news.name.contains(searchQuery, ignoreCase = true)
            }
        }
    }.value

    TvLazyColumn(
        modifier = modifier, state = tvLazyColumnState
    ) {
        item {
            Surface(
                shape = ClickableSurfaceDefaults.shape(shape = NewsCardShape),
                scale = ClickableSurfaceDefaults.scale(focusedScale = 1f),
                colors = ClickableSurfaceDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    pressedContainerColor = MaterialTheme.colorScheme.inverseOnSurface,
                    focusedContentColor = MaterialTheme.colorScheme.onSurface,
                    pressedContentColor = MaterialTheme.colorScheme.onSurface
                ),
                border = ClickableSurfaceDefaults.border(
                    focusedBorder = Border(
                        border = BorderStroke(
                            width = if (isTfFocused) 2.dp else 1.dp,
                            color = animateColorAsState(
                                targetValue = if (isTfFocused) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.border, label = ""
                            ).value
                        ), shape = NewsCardShape
                    )
                ),
                tonalElevation = 2.dp,
                modifier = Modifier
                    .padding(horizontal = childPadding.start)
                    .padding(top = 8.dp),
                onClick = { tfFocusRequester.requestFocus() }
            ) {
                BasicTextField(
                    value = searchQuery,
                    onValueChange = { updatedQuery -> searchQuery = updatedQuery },
                    decorationBox = {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .padding(start = 20.dp)
                        ) {
                            it()
                            if (searchQuery.isEmpty()) {
                                Text(
                                    modifier = Modifier.graphicsLayer { alpha = 0.6f },
                                    text = stringResource(R.string.search_screen_et_placeholder),
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .focusRequester(tfFocusRequester)
                        .align(AbsoluteAlignment.BottomLeft)
                        .onKeyEvent {
                            if (it.nativeKeyEvent.action == KeyEvent.ACTION_UP) {
                                when (it.nativeKeyEvent.keyCode) {
                                    KeyEvent.KEYCODE_DPAD_DOWN -> {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }

                                    KeyEvent.KEYCODE_DPAD_UP -> {
                                        focusManager.moveFocus(FocusDirection.Up)
                                    }

                                    KeyEvent.KEYCODE_BACK -> {
                                        focusManager.moveFocus(FocusDirection.Exit)
                                    }
                                }
                            }
                            true
                        },
                    cursorBrush = Brush.verticalGradient(
                        colors = listOf(
                            LocalContentColor.current,
                            LocalContentColor.current,
                        )
                    ),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false, imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        // Perform search action here
                    }),
                    maxLines = 1,
                    interactionSource = tfInteractionSource,
                    textStyle = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }

        item {
            NewsRow(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .padding(top = 4.dp),
                news = filteredNews,
                title = "", // Pass empty title since each news item is independent
                goToVideoPlayer = goToVideoPlayer
            )
        }
    }
}

@Composable
fun SearchScreen(
    goToVideoPlayer: (news: News) -> Unit,
) {
    val allNews = generateDummyMovies()
    SearchResult(
        modifier = Modifier,
        goToVideoPlayer = goToVideoPlayer,
        allNews = allNews
    )
}
