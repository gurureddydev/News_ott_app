package com.guru.demoottapp.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimens(
    val extraSmall: Dp = 0.dp,
    val small1: Dp = 0.dp,
    val small2: Dp = 0.dp,
    val small3: Dp = 0.dp,
    val medium1: Dp = 0.dp,
    val medium2: Dp = 0.dp,
    val medium3: Dp = 0.dp,
    val large: Dp = 0.dp,
    val height: Dp = 0.dp,
    val width: Dp = 0.dp,
    val buttonHeight: Dp = 40.dp,
    val logoSize: Dp = 42.dp,
    val textSizeSmall: TextUnit = 0.sp,
    val textSizeMedium: TextUnit = 0.sp,
    val largeHeight: Dp = 0.dp,
    val numRepetitionsPortrait: Int,
    val numRepetitionsLandscape: Int,
    val shimmerSize: Dp,
    val spacerWidth: Dp
)

val CompactSmallDimens = Dimens(
    small1 = 4.dp,
    small2 = 5.dp,
    small3 = 8.dp,
    medium1 = 16.dp,
    medium2 = 26.dp,
    medium3 = 30.dp,
    large = 45.dp,
    height = 55.dp,
    width = 75.dp,
    buttonHeight = 30.dp,
    logoSize = 36.dp,
    textSizeSmall = 4.sp,
    textSizeMedium = 6.sp,
    numRepetitionsPortrait = 6,
    numRepetitionsLandscape = 8,
    shimmerSize = 100.dp,
    spacerWidth = 8.dp
)

val CompactMediumDimens = Dimens(
    small1 = 8.dp,
    small2 = 13.dp,
    small3 = 17.dp,
    medium1 = 25.dp,
    medium2 = 30.dp,
    medium3 = 35.dp,
    large = 65.dp,
    largeHeight = 76.dp,
    height = 75.dp,
    width = 85.dp,
    textSizeSmall = 6.sp,
    textSizeMedium = 8.sp,
            numRepetitionsPortrait = 6,
    numRepetitionsLandscape = 8,
    shimmerSize = 100.dp,
    spacerWidth = 8.dp

)

val CompactDimens = Dimens(
    small1 = 4.dp,
    small2 = 8.dp,
    small3 = 16.dp,
    medium1 = 25.dp,
    medium2 = 30.dp,
    medium3 = 35.dp,
    large = 180.dp,
    largeHeight = 230.dp,
    height = 150.dp,
    width = 180.dp,
    textSizeSmall = 10.sp,
    textSizeMedium = 12.sp,
    numRepetitionsPortrait = 6,
    numRepetitionsLandscape = 8,
    shimmerSize = 100.dp,
    spacerWidth = 8.dp

)

val MediumDimens = Dimens(
    small1 = 7.dp,
    small2 = 16.dp,
    small3 = 20.dp,
    medium1 = 25.dp,
    medium2 = 30.dp,
    medium3 = 35.dp,
    large = 210.dp,
    largeHeight = 220.dp,
    logoSize = 55.dp,
    height = 150.dp,
    width = 200.dp,
    textSizeSmall = 8.sp,
    textSizeMedium = 12.sp,
    numRepetitionsPortrait = 8,
    numRepetitionsLandscape = 10,
    shimmerSize = 120.dp,
    spacerWidth = 10.dp
)

val ExpandedDimens = Dimens(
    small1 = 8.dp,
    small2 = 16.dp,
    small3 = 25.dp,
    medium1 = 30.dp,
    medium2 = 35.dp,
    medium3 = 45.dp,
    large = 300.dp,
    largeHeight = 400.dp,
    logoSize = 72.dp,
    height = 150.dp,
    width = 250.dp,
    textSizeSmall = 14.sp,
    textSizeMedium = 18.sp,
    numRepetitionsPortrait = 8,
    numRepetitionsLandscape = 10,
    shimmerSize = 120.dp,
    spacerWidth = 10.dp
)
