package com.guru.demoottapp.screens.search.keyboard.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.ui.graphics.vector.ImageVector
import com.guru.demoottapp.screens.search.keyboard.data.KeysConstants

sealed class TextUtilityKey(
    override val icon: ImageVector,
    override val text: String,
    override val span: Int = 1
) : UtilityKey(icon, text, span) {

    object Numeric : TextUtilityKey(
        Icons.Outlined.Numbers,
        KeysConstants.NUMERIC_KEY,
        2
    )

    object SpecialCharacters : TextUtilityKey(
        Icons.Outlined.Numbers,
        KeysConstants.SPECIAL_CHARACTERS_KEY
    )

    object ABC : TextUtilityKey(
        Icons.Outlined.Numbers,
        KeysConstants.ABC_KEY,
        2
    )
}