package com.example.cargo_customer.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Unified elevation and shadow tokens.
 *
 * @property none 0.dp
 * @property xs 1.dp
 * @property sm 2.dp
 * @property md 4.dp
 * @property lg 8.dp
 * @property xl 12.dp
 * @property xxl 16.dp
 */
@Immutable
data class AppElevation(
    val none: Dp = 0.dp,
    val xs: Dp = 1.dp,
    val sm: Dp = 2.dp,
    val md: Dp = 4.dp,
    val lg: Dp = 8.dp,
    val xl: Dp = 12.dp,
    val xxl: Dp = 16.dp,
)

/**
 * CompositionLocal for injecting UI elevations across layouts.
 */
val LocalAppElevation = staticCompositionLocalOf { AppElevation() }