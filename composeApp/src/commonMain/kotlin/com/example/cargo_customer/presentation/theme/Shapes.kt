package com.example.cargo_customer.presentation.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

// ============================================================================
// APP SHAPES
// ============================================================================
// Consistent corner radius scale aligned with the 4dp grid.
//
// WHY:  Shapes should NOT vary between light/dark themes (the previous code
//       doubled corner radii in dark mode — this is incorrect).
//       Shapes are spatial, not chromatic.
//
// SCALE: 4 → 8 → 12 → 16 → 24 → 28 → full
//        (follows Material 3's shape scale recommendations)
// ============================================================================

@Immutable
data class AppShapes(
    /** 4dp — Subtle rounding for small elements (chips, badges) */
    val extraSmall: RoundedCornerShape = RoundedCornerShape(4.dp),
    /** 8dp — Default rounding (text fields, small cards) */
    val small: RoundedCornerShape = RoundedCornerShape(8.dp),
    /** 12dp — Medium rounding (cards, dialogs) */
    val medium: RoundedCornerShape = RoundedCornerShape(12.dp),
    /** 16dp — Large rounding (bottom sheets, images) */
    val large: RoundedCornerShape = RoundedCornerShape(16.dp),
    /** 24dp — Extra large rounding (modals) */
    val extraLarge: RoundedCornerShape = RoundedCornerShape(24.dp),
    /** 28dp — Near-pill (FABs, navigation bars) */
    val extraExtraLarge: RoundedCornerShape = RoundedCornerShape(28.dp),
    /** Full circle — Avatars, circular buttons */
    val circle: Shape = CircleShape,
    /** 50% — Pill shape for buttons, tags */
    val pill: RoundedCornerShape = RoundedCornerShape(percent = 50),
)

// ============================================================================
// MATERIAL 3 SHAPES
// ============================================================================
// Maps our AppShapes → Material Shapes for MaterialTheme integration.
// ============================================================================

val AppMaterialShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp),
)

val LocalAppShapes = staticCompositionLocalOf { AppShapes() }
