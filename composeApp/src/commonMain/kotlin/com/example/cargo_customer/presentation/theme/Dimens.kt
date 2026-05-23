package com.example.cargo_customer.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AppSpacing(
    val none: Dp = 0.dp,
    val xxs: Dp = 2.dp,
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 20.dp,
    val xxl: Dp = 24.dp,
    val xxxl: Dp = 32.dp,
    val huge: Dp = 40.dp,
    val massive: Dp = 48.dp,
    val colossal: Dp = 64.dp,
)


@Immutable
data class AppSizing(
    val iconXs: Dp = 16.dp,
    val iconSm: Dp = 20.dp,
    val iconMd: Dp = 24.dp,
    val iconLg: Dp = 32.dp,
    val iconXl: Dp = 40.dp,

    val avatarSm: Dp = 32.dp,
    val avatarMd: Dp = 40.dp,
    val avatarLg: Dp = 56.dp,
    val avatarXl: Dp = 80.dp,

    val buttonHeightSm: Dp = 36.dp,
    val buttonHeightMd: Dp = 44.dp,
    val buttonHeightLg: Dp = 52.dp,
    val buttonHeightXl: Dp = 56.dp,

    val minTouchTarget: Dp = 48.dp,

    val topBarHeight: Dp = 56.dp,
    val bottomBarHeight: Dp = 80.dp,
)


@Immutable
data class AppDimensions(
    val spacing: AppSpacing = AppSpacing(),
    val sizing: AppSizing = AppSizing(),

    val screenPaddingHorizontal: Dp = 16.dp,
    val screenPaddingVertical: Dp = 16.dp,
    val cardPadding: Dp = 16.dp,
    val cardPaddingLarge: Dp = 20.dp,
    val listItemSpacing: Dp = 12.dp,
    val gridSpacing: Dp = 16.dp,
    val sectionGap: Dp = 24.dp,
    val maxContentWidth: Dp = 600.dp,
)

val LocalAppDimensions = staticCompositionLocalOf { AppDimensions() }
