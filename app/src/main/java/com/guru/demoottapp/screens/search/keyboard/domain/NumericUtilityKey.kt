package com.guru.demoottapp.screens.search.keyboard.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.KeyboardBackspace
import androidx.compose.material.icons.outlined.SpaceBar
import androidx.compose.ui.graphics.vector.ImageVector
import com.guru.demoottapp.screens.search.keyboard.data.KeysConstants

sealed class NumericUtilityKey(
    open val icon: ImageVector,
    override val text: String,
    override val span: Int = 1
) : Key {


    object Backspace : UtilityKey(
        Icons.Outlined.KeyboardBackspace,
        KeysConstants.BACK_SPACE_KEY,
        2
    )

    object Space : UtilityKey(
        Icons.Outlined.SpaceBar,
        KeysConstants.SPACE_KEY,
        6
    )

    object Clear : UtilityKey(
        Icons.Outlined.Delete,
        KeysConstants.CLEAR_KEY,
        2
    )
}