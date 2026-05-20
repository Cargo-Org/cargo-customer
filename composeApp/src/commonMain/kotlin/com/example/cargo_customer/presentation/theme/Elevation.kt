package com.example.cargo_customer.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// ============================================================================
// ELEVATION
// ============================================================================
// Centralized elevation tokens for consistent shadow depth.
//
// WHY:  Elevation creates visual hierarchy. A fixed scale prevents
//       random shadow values (e.g., `elevation = 7.dp`) and keeps
//       the depth model coherent.
//
// Material 3 uses tonal elevation (surface color shift) rather than
// drop shadows in dark mode. These tokens still apply for light mode
// and any custom shadow implementations.
// ============================================================================

@Immutable
data class AppElevation(
    /** 0dp — Flat / no elevation */
    val none: Dp = 0.dp,
    /** 1dp — Subtle lift (cards at rest) */
    val xs: Dp = 1.dp,
    /** 2dp — Default card elevation */
    val sm: Dp = 2.dp,
    /** 4dp — Raised elements (FAB at rest, menus) */
    val md: Dp = 4.dp,
    /** 8dp — Elevated dialogs, navigation drawers */
    val lg: Dp = 8.dp,
    /** 12dp — Top-level floating elements */
    val xl: Dp = 12.dp,
    /** 16dp — Maximum elevation (dragged cards, modal bottom sheets) */
    val xxl: Dp = 16.dp,
)

val LocalAppElevation = staticCompositionLocalOf { AppElevation() }
