package com.example.cargo_customer.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DisplayFont: FontFamily = FontFamily.SansSerif
private val BodyFont: FontFamily = FontFamily.SansSerif

@Immutable
data class AppTypography(
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,

    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,

    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,

    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,

    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
)


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
