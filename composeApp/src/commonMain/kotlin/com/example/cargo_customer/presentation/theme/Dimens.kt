package com.example.cargo_customer.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// ============================================================================
// SPACING
// ============================================================================
// Uniform spacing scale based on a 4dp grid system.
//
// WHY 4dp?  It aligns with Material Design's baseline grid, produces
//           visually harmonious spacing at every scale, and keeps values
//           predictable (every token is a multiple of 4).
//
// USAGE:    `CargoTheme.spacing.md` instead of hardcoded `16.dp`
// ============================================================================

@Immutable
data class AppSpacing(
    val none: Dp = 0.dp,
    /** 2dp — Hairline gaps (icon-to-text micro-spacing) */
    val xxs: Dp = 2.dp,
    /** 4dp — Tightest standard gap */
    val xs: Dp = 4.dp,
    /** 8dp — Small spacing (between related elements) */
    val sm: Dp = 8.dp,
    /** 12dp — Compact spacing */
    val md: Dp = 12.dp,
    /** 16dp — Default spacing (most common) */
    val lg: Dp = 16.dp,
    /** 20dp — Comfortable spacing */
    val xl: Dp = 20.dp,
    /** 24dp — Section-level spacing */
    val xxl: Dp = 24.dp,
    /** 32dp — Large section spacing */
    val xxxl: Dp = 32.dp,
    /** 40dp — Screen-level spacing */
    val huge: Dp = 40.dp,
    /** 48dp — Extra large */
    val massive: Dp = 48.dp,
    /** 64dp — Maximum spacing */
    val colossal: Dp = 64.dp,
)

// ============================================================================
// SIZING
// ============================================================================
// Consistent component sizes for icons, avatars, buttons, etc.
//
// WHY:  Prevents random icon and component sizes across screens.
//       A fixed scale makes the UI feel cohesive.
// ============================================================================

@Immutable
data class AppSizing(
    /** 16dp — Tiny icons */
    val iconXs: Dp = 16.dp,
    /** 20dp — Small icons */
    val iconSm: Dp = 20.dp,
    /** 24dp — Default icons */
    val iconMd: Dp = 24.dp,
    /** 32dp — Large icons */
    val iconLg: Dp = 32.dp,
    /** 40dp — Extra large icons */
    val iconXl: Dp = 40.dp,

    /** 32dp — Tiny avatar */
    val avatarSm: Dp = 32.dp,
    /** 40dp — Default avatar */
    val avatarMd: Dp = 40.dp,
    /** 56dp — Large avatar */
    val avatarLg: Dp = 56.dp,
    /** 80dp — Hero avatar (profile) */
    val avatarXl: Dp = 80.dp,

    /** 36dp — Compact button height */
    val buttonHeightSm: Dp = 36.dp,
    /** 44dp — Default button height (touch target compliant) */
    val buttonHeightMd: Dp = 44.dp,
    /** 52dp — Large button height */
    val buttonHeightLg: Dp = 52.dp,
    /** 56dp — FAB / primary action */
    val buttonHeightXl: Dp = 56.dp,

    /** 48dp — Minimum touch target per Material guidelines */
    val minTouchTarget: Dp = 48.dp,

    /** 56dp — App bar height */
    val topBarHeight: Dp = 56.dp,
    /** 80dp — Bottom navigation bar height */
    val bottomBarHeight: Dp = 80.dp,
)

// ============================================================================
// APP DIMENSIONS (Unified)
// ============================================================================
// Combines spacing, sizing, and layout-specific dimensions in one place.
//
// WHY:  Single source of truth for all spatial values.
//       `CargoTheme.dimens` gives developers everything they need.
// ============================================================================

@Immutable
data class AppDimensions(
    val spacing: AppSpacing = AppSpacing(),
    val sizing: AppSizing = AppSizing(),

    // — Layout-specific —
    /** Horizontal page margin */
    val screenPaddingHorizontal: Dp = 16.dp,
    /** Vertical page margin */
    val screenPaddingVertical: Dp = 16.dp,
    /** Internal card padding */
    val cardPadding: Dp = 16.dp,
    /** Card padding for emphasized cards */
    val cardPaddingLarge: Dp = 20.dp,
    /** Gap between list items */
    val listItemSpacing: Dp = 12.dp,
    /** Gap between grid items */
    val gridSpacing: Dp = 16.dp,
    /** Section header to content spacing */
    val sectionGap: Dp = 24.dp,
    /** Content width cap for large screens */
    val maxContentWidth: Dp = 600.dp,
)

val LocalAppDimensions = staticCompositionLocalOf { AppDimensions() }
