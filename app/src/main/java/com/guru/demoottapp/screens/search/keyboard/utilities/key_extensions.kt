package com.guru.demoottapp.screens.search.keyboard.utilities

import com.guru.demoottapp.screens.search.keyboard.data.KeysDataSource
import com.guru.demoottapp.screens.search.keyboard.domain.Key
import com.guru.demoottapp.screens.search.keyboard.domain.NumericUtilityKey
import com.guru.demoottapp.screens.search.keyboard.domain.TextUtilityKey
import com.guru.demoottapp.screens.search.keyboard.domain.UtilityKey


fun Key.isBackspace() = this is UtilityKey.Backspace || this is NumericUtilityKey.Backspace
fun Key.isUppercase() = this is UtilityKey.Uppercase
fun Key.isToggleKey() = KeysDataSource.toggleKeys.contains(this)
fun Key.isAction() = this is UtilityKey.ActionArrow
fun Key.isNumeric() = this is TextUtilityKey.Numeric
fun Key.isAbc() = this is TextUtilityKey.ABC
fun Key.isClear() = this is UtilityKey.Clear
fun Key.isSpecialCharacters() = this is TextUtilityKey.SpecialCharacters
fun Key.handleCaseMode(isUppercaseEnabled: Boolean) =
    if (isUppercaseEnabled)
        text.uppercase()
    else
        text.uppercase()