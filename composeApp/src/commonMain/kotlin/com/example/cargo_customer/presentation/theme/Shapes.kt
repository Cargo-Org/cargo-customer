package com.example.cargo_customer.presentation.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * Custom design system shape tokens.
 *
 * @property extraSmall 4.dp
 * @property small 8.dp
 * @property medium 12.dp
 * @property large 16.dp
 * @property extraLarge 24.dp
 * @property extraExtraLarge 28.dp
 * @property circle Full circular clipping boundary
 * @property pill Pill container shape (50 percent rounded edge)
 */
@Immutable
data class AppShapes(
    val extraSmall: RoundedCornerShape = RoundedCornerShape(4.dp),
    val small: RoundedCornerShape = RoundedCornerShape(8.dp),
    val medium: RoundedCornerShape = RoundedCornerShape(12.dp),
    val large: RoundedCornerShape = RoundedCornerShape(16.dp),
    val extraLarge: RoundedCornerShape = RoundedCornerShape(24.dp),
    val extraExtraLarge: RoundedCornerShape = RoundedCornerShape(28.dp),
    val circle: Shape = CircleShape,
    val pill: RoundedCornerShape = RoundedCornerShape(percent = 50),
)

/**
 * Material 3 subsystem compatible fallback shape definitions.
 */
val AppMaterialShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp),
)

/**
 * CompositionLocal for injecting UI shapes across layouts.
 */
val LocalAppShapes = staticCompositionLocalOf { AppShapes() }