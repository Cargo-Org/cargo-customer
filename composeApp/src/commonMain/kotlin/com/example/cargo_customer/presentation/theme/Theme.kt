package com.example.cargo_customer.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

// ============================================================================
// DARK-THEME SIGNAL
// ============================================================================

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
// WHY an object?
//   Provides a discoverable, IDE-friendly namespace for all tokens.
//   Developers type `CargoTheme.` and get autocomplete for every
//   design system token — no need to memorize composition locals.
// ============================================================================

object CargoTheme {

    /** Whether the current composition is in dark mode */
    val isDarkTheme: Boolean
        @Composable @ReadOnlyComposable get() = LocalIsDarkTheme.current

    /** Material 3 color scheme (primary, surface, error, etc.) */
    val colorScheme
        @Composable @ReadOnlyComposable get() = MaterialTheme.colorScheme

    /** Extended semantic colors (success, warning, info, brand, etc.) */
    val extendedColors: ExtendedColorScheme
        @Composable @ReadOnlyComposable get() = LocalExtendedColors.current

    /** App typography scale */
    val typography: AppTypography
        @Composable @ReadOnlyComposable get() = LocalAppTypography.current

    /** Shape tokens */
    val shapes: AppShapes
        @Composable @ReadOnlyComposable get() = LocalAppShapes.current

    /** Spacing, sizing, and layout dimensions */
    val dimens: AppDimensions
        @Composable @ReadOnlyComposable get() = LocalAppDimensions.current

    /** Elevation tokens */
    val elevation: AppElevation
        @Composable @ReadOnlyComposable get() = LocalAppElevation.current
}

// ============================================================================
// CARGO THEME COMPOSABLE
// ============================================================================
// Wraps MaterialTheme and injects all custom composition locals.
//
// USAGE (in App.kt):
//   CargoCustomerTheme {
//       // your screens here
//   }
// ============================================================================

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
