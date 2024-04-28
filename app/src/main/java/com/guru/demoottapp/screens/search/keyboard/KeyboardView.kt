package com.guru.demoottapp.screens.search.keyboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guru.demoottapp.screens.search.keyboard.domain.Key
import com.guru.demoottapp.screens.search.keyboard.data.KeysDataSource
import com.guru.demoottapp.screens.search.keyboard.domain.NumericUtilityKey
import com.guru.demoottapp.screens.search.keyboard.domain.TextUtilityKey
import com.guru.demoottapp.screens.search.keyboard.utilities.append
import com.guru.demoottapp.screens.search.keyboard.utilities.clear
import com.guru.demoottapp.screens.search.keyboard.utilities.handleCaseMode
import com.guru.demoottapp.screens.search.keyboard.utilities.isAbc
import com.guru.demoottapp.screens.search.keyboard.utilities.isAction
import com.guru.demoottapp.screens.search.keyboard.utilities.isBackspace
import com.guru.demoottapp.screens.search.keyboard.utilities.isClear
import com.guru.demoottapp.screens.search.keyboard.utilities.isNumeric
import com.guru.demoottapp.screens.search.keyboard.utilities.isSpecialCharacters
import com.guru.demoottapp.screens.search.keyboard.utilities.isToggleKey
import com.guru.demoottapp.screens.search.keyboard.utilities.isUppercase
import com.guru.demoottapp.screens.search.keyboard.utilities.toggle
import com.guru.demoottapp.screens.search.keyboard.utilities.updateAndRemoveLastChar

@Composable
fun KeyboardView(
    modifier: Modifier = Modifier,
    textFieldState: MutableState<TextFieldValue>?,
    focusFirstKey: Boolean = false,
    enableEmailSuggestions: Boolean = true,
    onAction: ((key: Key) -> Unit)? = null,
    onKeyPress: (key: Key) -> Unit
) {
    val focusKey = remember { mutableStateOf(focusFirstKey) }
    val isUppercase = remember { mutableStateOf(false) }
    val isNumeric = remember { mutableStateOf(false) }
    val isSpecialCharacters = remember { mutableStateOf(false) }
    val alphabets = remember { mutableStateOf(KeysDataSource.normalKeys) }
    val numericKeys = remember { mutableStateOf(KeysDataSource.numericKeys) }
    val specialCharactersKeys = remember { mutableStateOf(KeysDataSource.specialCharactersKeys) }

    val keys by rememberUpdatedState(
        if (isNumeric.value) {
            numericKeys.value
        } else if (isSpecialCharacters.value) {
            specialCharactersKeys.value
        } else {
            alphabets.value
        }
    )
    Column(
        modifier = modifier
//            .background(
//                MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium
//            )
            .padding(1.dp)
    ) {
//        if (enableEmailSuggestions)
//            EmailSuggestionsRow(textFieldState) {}

        LazyVerticalGrid(
            columns = GridCells.Fixed(10)
        ) {
            items(keys.size,
                key = { index ->
                    keys[index].text
                },
                span = { index ->
                    GridItemSpan(keys[index].span)
                }) { index ->
                KeyboardButton(
                    key = keys[index],
                    requestFocus = focusKey.value && index == 0,
                    isUppercaseEnable = isUppercase.value,
                    isToggle = keys[index].isToggleKey(),
                ) {
                    if (it.isUppercase()) {
                        isUppercase.toggle()
                    } else if (it.isAction()) {
                        onAction?.invoke(it)
                    } else if (it.isSpecialCharacters()) {
                        isSpecialCharacters.toggle()
                        isNumeric.value = false
                    } else if (it.isNumeric() || it.isAbc()) {
                        isNumeric.toggle()
                        isSpecialCharacters.value = false
                    } else {
                        onKeyPress(it)
                        processKeys(it, textFieldState, isUppercase.value)
                    }
                }
            }
        }
    }
}

fun processKeys(it: Key, state: MutableState<TextFieldValue>?, isUppercase: Boolean) {
    if (it.isBackspace()) {
        state?.updateAndRemoveLastChar()
    } else if (it.isClear()) {
        state?.clear()
    } else {
        state?.append(it.handleCaseMode(isUppercase))
    }
}

@Preview
@Composable
fun KeyboardViewPreview() {
    KeyboardView(textFieldState = null) {}
}