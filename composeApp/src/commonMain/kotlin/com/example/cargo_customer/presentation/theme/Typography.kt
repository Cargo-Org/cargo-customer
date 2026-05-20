package com.example.cargo_customer.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ============================================================================
// FONT FAMILIES
// ============================================================================
// Centralize font family declarations so swapping fonts is a single change.
// In Compose Multiplatform, you can load platform-specific fonts here.
// Until custom fonts are bundled, we alias to system sans-serif.
// ============================================================================

private val DisplayFont: FontFamily = FontFamily.SansSerif
private val BodyFont: FontFamily = FontFamily.SansSerif

// ============================================================================
// APP TEXT STYLES
// ============================================================================
// Semantic typography scale following Material 3 naming conventions:
//   Display → Headlines → Title → Body → Label
// Each level has Large / Medium / Small variants.
//
// WHY:  A consistent scale avoids ad-hoc font sizes scattered across the app.
//       Semantic names make intent clear — `titleLarge` tells you *where*
//       a style should be used, not *how big* it is.
// ============================================================================

@Immutable
data class AppTypography(
    // — Display —  (Hero / Splash / Marketing screens)
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,

    // — Headline —  (Top-level section headings)
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,

    // — Title —  (Card titles, dialog titles, toolbar)
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,

    // — Body —  (Paragraphs, descriptions, primary content)
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,

    // — Label —  (Buttons, chips, captions, metadata)
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
)

// ============================================================================
// TYPOGRAPHY INSTANCE
// ============================================================================
// Single instance — typography does NOT change between light/dark modes.
// Only *color* changes per theme; sizes and weights stay identical.
// Colors are applied at the composable level via `color = CargoTheme.colorScheme.onSurface`.
// ============================================================================

val AppTypographyInstance = AppTypography(
    // Display
    displayLarge = TextStyle(
        fontFamily = DisplayFont,
        fontSize = 57.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp,
    ),
    displayMedium = TextStyle(
        fontFamily = DisplayFont,
        fontSize = 45.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 52.sp,
        letterSpacing = 0.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = DisplayFont,
        fontSize = 36.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 44.sp,
        letterSpacing = 0.sp,
    ),

    // Headline
    headlineLarge = TextStyle(
        fontFamily = DisplayFont,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp,
        letterSpacing = (-0.02).sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = DisplayFont,
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 36.sp,
        letterSpacing = 0.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = DisplayFont,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
    ),

    // Title
    titleLarge = TextStyle(
        fontFamily = DisplayFont,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = BodyFont,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = BodyFont,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),

    // Body
    bodyLarge = TextStyle(
        fontFamily = BodyFont,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = BodyFont,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = BodyFont,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
    ),

    // Label
    labelLarge = TextStyle(
        fontFamily = BodyFont,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = BodyFont,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = BodyFont,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
    ),
)

// ============================================================================
// MATERIAL 3 TYPOGRAPHY
// ============================================================================
// Maps our AppTypography → Material Typography for MaterialTheme integration.
// ============================================================================

val AppMaterialTypography = Typography(
    displayLarge = AppTypographyInstance.displayLarge,
    displayMedium = AppTypographyInstance.displayMedium,
    displaySmall = AppTypographyInstance.displaySmall,
    headlineLarge = AppTypographyInstance.headlineLarge,
    headlineMedium = AppTypographyInstance.headlineMedium,
    headlineSmall = AppTypographyInstance.headlineSmall,
    titleLarge = AppTypographyInstance.titleLarge,
    titleMedium = AppTypographyInstance.titleMedium,
    titleSmall = AppTypographyInstance.titleSmall,
    bodyLarge = AppTypographyInstance.bodyLarge,
    bodyMedium = AppTypographyInstance.bodyMedium,
    bodySmall = AppTypographyInstance.bodySmall,
    labelLarge = AppTypographyInstance.labelLarge,
    labelMedium = AppTypographyInstance.labelMedium,
    labelSmall = AppTypographyInstance.labelSmall,
)

val LocalAppTypography = staticCompositionLocalOf { AppTypographyInstance }
