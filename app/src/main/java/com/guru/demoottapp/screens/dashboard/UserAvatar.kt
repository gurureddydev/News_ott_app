package com.guru.demoottapp.screens.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.ToggleableSurfaceDefaults
import com.guru.demoottapp.ui.theme.NewsBorderWidth

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun UserAvatar(
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        shape = ToggleableSurfaceDefaults.shape(shape = CircleShape),
        border = ToggleableSurfaceDefaults.border(
            focusedBorder = Border(
                border = BorderStroke(
                    width = NewsBorderWidth,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                shape = CircleShape
            ),
            selectedBorder = Border(
                border = BorderStroke(
                    width = NewsBorderWidth,
                    color = MaterialTheme.colorScheme.primary
                ),
                shape = CircleShape
            ),
        ),
        scale = ToggleableSurfaceDefaults.scale(focusedScale = 1f),
        modifier = modifier.size(32.dp),
        checked = selected,
        onCheckedChange = { onClick() }
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
