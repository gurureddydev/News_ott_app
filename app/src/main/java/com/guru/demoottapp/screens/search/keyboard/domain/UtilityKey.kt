package com.guru.demoottapp.screens.search.keyboard.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.guru.demoottapp.screens.search.keyboard.data.KeysConstants

sealed class UtilityKey(
    open val icon: ImageVector,
    override val text: String,
    override val span: Int = 1
) : Key {
    object Uppercase : UtilityKey(
        Icons.Outlined.KeyboardCapslock,
        KeysConstants.UPPER_CASE_KEY
    )

    object Backspace : UtilityKey(
        Icons.Outlined.KeyboardBackspace,
        KeysConstants.BACK_SPACE_KEY,
        2
    )

    object Clear : UtilityKey(
        Icons.Outlined.Clear,
        KeysConstants.CLEAR_KEY,
        2
    )

    object Space : UtilityKey(
        Icons.Outlined.SpaceBar,
        KeysConstants.SPACE_KEY,
        8
    )

    object ActionArrow : UtilityKey(
        Icons.Outlined.Search,
        KeysConstants.SEARCH_KEY,
        2
    )
}