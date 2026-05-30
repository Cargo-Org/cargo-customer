package com.example.cargo_customer.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Global layout spacing tokens.
 *
 * @property none 0.dp
 * @property xxs 2.dp
 * @property xs 4.dp
 * @property sm 8.dp
 * @property md 12.dp
 * @property lg 16.dp
 * @property xl 20.dp
 * @property xxl 24.dp
 * @property xxxl 32.dp
 * @property huge 40.dp
 * @property massive 48.dp
 * @property colossal 64.dp
 */
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

/**
 * Standard element and component sizing tokens.
 *
 * @property iconXs 16.dp
 * @property iconSm 20.dp
 * @property iconMd 24.dp
 * @property iconLg 32.dp
 * @property iconXl 40.dp
 * @property avatarSm 32.dp
 * @property avatarMd 40.dp
 * @property avatarLg 56.dp
 * @property avatarXl 80.dp
 * @property buttonHeightSm 36.dp
 * @property buttonHeightMd 44.dp
 * @property buttonHeightLg 52.dp
 * @property buttonHeightXl 56.dp
 * @property minTouchTarget 48.dp (Accessibility standard)
 * @property topBarHeight 56.dp
 * @property bottomBarHeight 80.dp
 */
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

/**
 * Structural page layout and padding rules.
 *
 * @property screenPaddingHorizontal 16.dp
 * @property screenPaddingVertical 16.dp
 * @property cardPadding 16.dp
 * @property cardPaddingLarge 20.dp
 * @property listItemSpacing 12.dp
 * @property gridSpacing 16.dp
 * @property sectionGap 24.dp
 * @property maxContentWidth 600.dp (Tablet/Desktop container limit)
 */
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

/**
 * CompositionLocal for injecting UI dimensions across layouts.
 */
val LocalAppDimensions = staticCompositionLocalOf { AppDimensions() }