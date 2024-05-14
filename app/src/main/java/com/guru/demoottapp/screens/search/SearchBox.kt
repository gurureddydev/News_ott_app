package com.guru.demoottapp.screens.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.guru.demoottapp.screens.search.keyboard.KeyboardView

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    searchQuery: MutableState<String>,
    onSearchQueryChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .border(
                BorderStroke(1.dp, Color.LightGray),
                shape = MaterialTheme.shapes.small
            )
            .defaultMinSize(minWidth = 300.dp)
            .padding(16.dp)
    ) {
        Text(
            text = searchQuery.value,
        )
    }
}

@Composable
fun ImageList(images: List<ImageItem>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
    ) {
        items(images) { image ->
            Images(
                painter = painterResource(id = image.resourceId),
                contentDescription = image.name,
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun Images(
    painter: androidx.compose.ui.graphics.painter.Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
    )
}

@Composable
fun SearchScreen(images: List<ImageItem>, searchQuery: String) {
    val filteredImages = remember {
        derivedStateOf {
            if (searchQuery.isEmpty()) {
                images
            } else {
                images.filter { it.name.contains(searchQuery, ignoreCase = true) }
            }
        }
    }.value

    Column(Modifier.fillMaxSize()) {
        ImageList(images = filteredImages)
    }
}

data class ImageItem(val name: String, val resourceId: Int)

@Composable
fun SearchKeyboardWithScreen(images: List<ImageItem>) {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.CenterStart, // Align content to the left side
        modifier = Modifier
            .width(500.dp) // Increase width to fit both components
            .height(400.dp)
    ) {
        Row(Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(24.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    CustomTextField(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        searchQuery = remember { mutableStateOf(searchQuery) },
                        onSearchQueryChange = { newQuery ->
                            searchQuery = newQuery
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(24.dp)
            ) {
                SearchScreen(images = images, searchQuery = searchQuery)
            }
        }
    }
}
