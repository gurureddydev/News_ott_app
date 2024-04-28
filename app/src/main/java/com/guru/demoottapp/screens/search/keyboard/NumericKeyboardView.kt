package com.guru.demoottapp.screens.search.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.demoottapp.screens.search.keyboard.domain.Key
import com.guru.demoottapp.screens.search.keyboard.data.KeysDataSource

@Composable
fun NumericKeyboardView(modifier: Modifier = Modifier, onKeyPress: (key: Key) -> Unit) {
    val keys = remember { KeysDataSource.numericMiniKeys }
    LazyVerticalGrid(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp),
        columns = GridCells.Fixed(4)
    ) {
        items(keys.size) { index ->
            KeyboardButton(key = keys[index], requestFocus = false) {
                onKeyPress(it)
            }
        }
    }
}

@Preview
@Composable
fun NumericKeyboardViewPreview() {
    NumericKeyboardView {}
}