package com.guru.demoottapp.screens.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.guru.demoottapp.screens.search.keyboard.KeyboardView

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    textState: MutableState<TextFieldValue>
) {
    val username = remember { textState }
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
            text = if (username.value.text.isEmpty()) "Start typing " else username.value.text,
            color = Color.White // Set text color to white here
        )
    }
}


@Composable
fun SearchKeyboard() {
    Box(
        contentAlignment = Alignment.CenterStart, // Align content to the left side
        modifier = Modifier
            .width(500.dp)
            .height(400.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            val username = remember { mutableStateOf(TextFieldValue(text = "")) }
            CustomTextField(textState = username)
            Spacer(modifier = Modifier.height(24.dp))
            KeyboardView(
                textFieldState = username,
                focusFirstKey = true,
                modifier = Modifier.shadow(8.dp),
                onAction = {}
            ) {
            }
        }
    }
}
