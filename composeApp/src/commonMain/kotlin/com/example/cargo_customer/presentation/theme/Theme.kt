package com.example.cargo_customer.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

private val LocalIsDarkTheme = compositionLocalOf { false }

// ============================================================================
// CARGO THEME — UNIFIED ACCESSOR OBJECT
// ============================================================================
// Single entry point for all design tokens in the app.
//
// USAGE:
//   CargoTheme.colorScheme.primary          → Material color roles
//   CargoTheme.extendedColors.success       → Custom semantic colors
//   CargoTheme.typography.headlineLarge      → Text styles
//   CargoTheme.shapes.medium                → Corner shapes
//   CargoTheme.dimens.spacing.md            → Spacing tokens
//   CargoTheme.dimens.sizing.iconMd         → Component sizes
//   CargoTheme.elevation.sm                 → Shadow elevation
//   CargoTheme.isDarkTheme                  → Current mode check
//


object CargoTheme {

    val isDarkTheme: Boolean
        @Composable @ReadOnlyComposable get() = LocalIsDarkTheme.current

    val colorScheme
        @Composable @ReadOnlyComposable get() = MaterialTheme.colorScheme

    val extendedColors: ExtendedColorScheme
        @Composable @ReadOnlyComposable get() = LocalExtendedColors.current

    val typography: AppTypography
        @Composable @ReadOnlyComposable get() = LocalAppTypography.current

    val shapes: AppShapes
        @Composable @ReadOnlyComposable get() = LocalAppShapes.current

    val dimens: AppDimensions
        @Composable @ReadOnlyComposable get() = LocalAppDimensions.current

    val elevation: AppElevation
        @Composable @ReadOnlyComposable get() = LocalAppElevation.current
}


@Composable
fun CargoCustomerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors

    CompositionLocalProvider(
        LocalIsDarkTheme provides darkTheme,
        LocalExtendedColors provides extendedColors,
        LocalAppTypography provides AppTypographyInstance,
        LocalAppShapes provides AppShapes(),
        LocalAppDimensions provides AppDimensions(),
        LocalAppElevation provides AppElevation(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppMaterialTypography,
            shapes = AppMaterialShapes,
            content = content,
        )
    }
}
