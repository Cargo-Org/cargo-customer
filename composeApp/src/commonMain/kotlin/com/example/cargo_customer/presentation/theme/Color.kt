package com.example.cargo_customer.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// ============================================================================
// PALETTE TOKENS
// ============================================================================
// Raw color values organized by hue family.
// These are NEVER used directly in composables — only referenced here
// to build the semantic color schemes below.
// ============================================================================

// — Brand / Primary (Deep Navy Blue) —
private object PrimaryPalette {
    val primary10 = Color(0xFF080C14)
    val primary20 = Color(0xFF0B1220)
    val primary30 = Color(0xFF0F172A) // #0F172A (Primary: Trust & Depth - Deep Navy Blue)
    val primary40 = Color(0xFF1E2E4F)
    val primary50 = Color(0xFF2C4373)
    val primary60 = Color(0xFF3B5998)
    val primary70 = Color(0xFF627CAD)
    val primary80 = Color(0xFF8FA1C4)
    val primary90 = Color(0xFFC7D1E5)
    val primary95 = Color(0xFFE3E8F2)
    val primary99 = Color(0xFFF5F7FA)
}

// — Secondary (Vibrant Safety Orange) —
private object SecondaryPalette {
    val secondary10 = Color(0xFF3E0A00)
    val secondary20 = Color(0xFF671100)
    val secondary30 = Color(0xFF9E1F00)
    val secondary40 = Color(0xFFD03D0D)
    val secondary50 = Color(0xFFFF5722) // #FF5722 (Secondary: Speed & Alertness - Vibrant Safety Orange)
    val secondary60 = Color(0xFFFF784E)
    val secondary70 = Color(0xFFFF997A)
    val secondary80 = Color(0xFFFFBBA7)
    val secondary90 = Color(0xFFFFDDD3)
    val secondary95 = Color(0xFFFFF0EC)
    val secondary99 = Color(0xFFFFFDFD)
}

// — Tertiary (Electric Blue Accent) —
private object TertiaryPalette {
    val tertiary10 = Color(0xFF00123D)
    val tertiary20 = Color(0xFF002275)
    val tertiary30 = Color(0xFF0038A8)
    val tertiary40 = Color(0xFF1D4ED8)
    val tertiary50 = Color(0xFF2563EB) // #2563EB (Accent: Modern Tech - Electric Blue)
    val tertiary60 = Color(0xFF3B82F6)
    val tertiary70 = Color(0xFF60A5FA)
    val tertiary80 = Color(0xFF93C5FD)
    val tertiary90 = Color(0xFFDBEAFE)
    val tertiary95 = Color(0xFFEFF6FF)
    val tertiary99 = Color(0xFFF8FAFC)
}

// — Neutral (Cool Slate / Navy Grays, Crisp Ice White & Dark Charcoal) —
private object NeutralPalette {
    val neutral0 = Color(0xFF000000)
    val neutral4 = Color(0xFF080C14)
    val neutral6 = Color(0xFF0F172A) // Sleek Deep Navy dark mode background base (#0F172A)
    val neutral10 = Color(0xFF1E293B) // #1E293B (Text / Dark Neutral: Dark Charcoal)
    val neutral12 = Color(0xFF242E42)
    val neutral17 = Color(0xFF2E3B52)
    val neutral20 = Color(0xFF334155)
    val neutral22 = Color(0xFF3E4E68)
    val neutral24 = Color(0xFF475569)
    val neutral30 = Color(0xFF64748B)
    val neutral40 = Color(0xFF708090)
    val neutral50 = Color(0xFF8A9BA8)
    val neutral60 = Color(0xFF94A3B8)
    val neutral70 = Color(0xFFCBD5E1)
    val neutral80 = Color(0xFFE2E8F0)
    val neutral87 = Color(0xFFEDF2F7)
    val neutral90 = Color(0xFFF1F5F9)
    val neutral92 = Color(0xFFF4F6F9)
    val neutral94 = Color(0xFFF5F7FA)
    val neutral95 = Color(0xFFF8FAFC) // #F8FAFC (Background / Light Neutral: Crisp Ice White)
    val neutral96 = Color(0xFFF9FAFB)
    val neutral98 = Color(0xFFFAFBFC)
    val neutral99 = Color(0xFFFCFDFE)
    val neutral100 = Color(0xFFFFFFFF) // Pure Solid White Background for surfaces/cards
}

// — Neutral Variant (Outline Grays) —
private object NeutralVariantPalette {
    val nv10 = Color(0xFF0F172A)
    val nv20 = Color(0xFF1E293B)
    val nv30 = Color(0xFF334155)
    val nv40 = Color(0xFF475569)
    val nv50 = Color(0xFF64748B)
    val nv60 = Color(0xFF94A3B8)
    val nv70 = Color(0xFFCBD5E1)
    val nv80 = Color(0xFFE2E8F0)
    val nv90 = Color(0xFFF1F5F9)
    val nv95 = Color(0xFFF8FAFC)
}

// — Semantic Status Colors —
private object StatusPalette {
    // Error (Red)
    val error10 = Color(0xFF410002)
    val error20 = Color(0xFF690005)
    val error30 = Color(0xFF93000A)
    val error40 = Color(0xFFBA1A1A)
    val error80 = Color(0xFFFFB4AB)
    val error90 = Color(0xFFFFDAD6)
    val error95 = Color(0xFFFFEDEA)

    // Success (Green)
    val success10 = Color(0xFF002209)
    val success20 = Color(0xFF053916)
    val success30 = Color(0xFF0E5C29)
    val success40 = Color(0xFF27AE60) // #27AE60 (Target success)
    val success80 = Color(0xFF72D594)
    val success90 = Color(0xFFA3E9B9)
    val success95 = Color(0xFFD1F6DC)

    // Warning (Amber)
    val warning10 = Color(0xFF261A00)
    val warning20 = Color(0xFF402D00)
    val warning30 = Color(0xFF5C4200)
    val warning40 = Color(0xFF7A5900)
    val warning80 = Color(0xFFF9BD48)
    val warning90 = Color(0xFFFFDEA1)
    val warning95 = Color(0xFFFFEED3)

    // Info (Blue)
    val info10 = Color(0xFF001D35)
    val info20 = Color(0xFF003356)
    val info30 = Color(0xFF004A79)
    val info40 = Color(0xFF00639E)
    val info80 = Color(0xFF96CCFF)
    val info90 = Color(0xFFCEE5FF)
    val info95 = Color(0xFFE8F1FF)
}

// ============================================================================
// EXTENDED SEMANTIC COLORS
// ============================================================================
// Custom semantic colors that extend beyond Material 3's built-in scheme.
// Accessed via `CargoTheme.extendedColors`.
// ============================================================================

@Immutable
data class ExtendedColorScheme(
    // — Status —
    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val warning: Color,
    val onWarning: Color,
    val warningContainer: Color,
    val onWarningContainer: Color,
    val info: Color,
    val onInfo: Color,
    val infoContainer: Color,
    val onInfoContainer: Color,

    // — Semantic Surface Roles —
    val cardBackground: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textDisabled: Color,
    val border: Color,
    val borderVariant: Color,
    val divider: Color,
    val shimmer: Color,
    val overlay: Color,

    // — Brand —
    val brandAccent: Color,
    val onBrandAccent: Color,
)

val LightExtendedColors = ExtendedColorScheme(
    // Status
    success = StatusPalette.success40,
    onSuccess = NeutralPalette.neutral100,
    successContainer = StatusPalette.success90,
    onSuccessContainer = StatusPalette.success10,
    warning = StatusPalette.warning40,
    onWarning = NeutralPalette.neutral100,
    warningContainer = StatusPalette.warning90,
    onWarningContainer = StatusPalette.warning10,
    info = StatusPalette.info40,
    onInfo = NeutralPalette.neutral100,
    infoContainer = StatusPalette.info90,
    onInfoContainer = StatusPalette.info10,

    // Semantic Surface
    cardBackground = NeutralPalette.neutral100,
    textPrimary = NeutralPalette.neutral10,
    textSecondary = NeutralPalette.neutral40,
    textTertiary = NeutralPalette.neutral50,
    textDisabled = NeutralPalette.neutral70,
    border = NeutralVariantPalette.nv80,
    borderVariant = NeutralVariantPalette.nv90,
    divider = NeutralVariantPalette.nv90,
    shimmer = NeutralPalette.neutral90,
    overlay = Color(0x52000000),

    // Brand
    brandAccent = TertiaryPalette.tertiary50,
    onBrandAccent = NeutralPalette.neutral100,
)

val DarkExtendedColors = ExtendedColorScheme(
    // Status
    success = StatusPalette.success80,
    onSuccess = StatusPalette.success20,
    successContainer = StatusPalette.success30,
    onSuccessContainer = StatusPalette.success90,
    warning = StatusPalette.warning80,
    onWarning = StatusPalette.warning20,
    warningContainer = StatusPalette.warning30,
    onWarningContainer = StatusPalette.warning90,
    info = StatusPalette.info80,
    onInfo = StatusPalette.info20,
    infoContainer = StatusPalette.info30,
    onInfoContainer = StatusPalette.info90,

    // Semantic Surface
    cardBackground = NeutralPalette.neutral12,
    textPrimary = NeutralPalette.neutral90,
    textSecondary = NeutralPalette.neutral60,
    textTertiary = NeutralPalette.neutral50,
    textDisabled = NeutralPalette.neutral30,
    border = NeutralVariantPalette.nv30,
    borderVariant = NeutralVariantPalette.nv20,
    divider = NeutralVariantPalette.nv20,
    shimmer = NeutralPalette.neutral17,
    overlay = Color(0x99000000),

    // Brand
    brandAccent = TertiaryPalette.tertiary80,
    onBrandAccent = TertiaryPalette.tertiary20,
)

val LocalExtendedColors = staticCompositionLocalOf { LightExtendedColors }

// ============================================================================
// MATERIAL 3 COLOR SCHEMES
// ============================================================================
// Maps palette tokens → Material 3 ColorScheme roles.
// ============================================================================

val LightColorScheme: ColorScheme = lightColorScheme(
    primary = PrimaryPalette.primary30,
    onPrimary = NeutralPalette.neutral100,
    primaryContainer = PrimaryPalette.primary90,
    onPrimaryContainer = PrimaryPalette.primary10,
    inversePrimary = PrimaryPalette.primary80,

    secondary = SecondaryPalette.secondary50,
    onSecondary = NeutralPalette.neutral100,
    secondaryContainer = SecondaryPalette.secondary90,
    onSecondaryContainer = SecondaryPalette.secondary10,

    tertiary = TertiaryPalette.tertiary50,
    onTertiary = NeutralPalette.neutral100,
    tertiaryContainer = TertiaryPalette.tertiary90,
    onTertiaryContainer = TertiaryPalette.tertiary10,

    error = StatusPalette.error40,
    onError = NeutralPalette.neutral100,
    errorContainer = StatusPalette.error90,
    onErrorContainer = StatusPalette.error10,

    background = NeutralPalette.neutral95,
    onBackground = NeutralPalette.neutral10,

    surface = NeutralPalette.neutral95,
    onSurface = NeutralPalette.neutral10,
    surfaceVariant = NeutralVariantPalette.nv95,
    onSurfaceVariant = NeutralVariantPalette.nv30,

    outline = NeutralVariantPalette.nv50,
    outlineVariant = NeutralVariantPalette.nv80,
    scrim = NeutralPalette.neutral0.copy(alpha = 0.38f),

    inverseSurface = NeutralPalette.neutral20,
    inverseOnSurface = NeutralPalette.neutral95,

    surfaceDim = NeutralPalette.neutral95,
    surfaceBright = NeutralPalette.neutral100,
    surfaceContainerLowest = NeutralPalette.neutral100,
    surfaceContainerLow = NeutralPalette.neutral98,
    surfaceContainer = NeutralPalette.neutral96,
    surfaceContainerHigh = NeutralPalette.neutral94,
    surfaceContainerHighest = NeutralPalette.neutral92,
    surfaceTint = PrimaryPalette.primary30,
)

val DarkColorScheme: ColorScheme = darkColorScheme(
    primary = PrimaryPalette.primary80,
    onPrimary = PrimaryPalette.primary20,
    primaryContainer = PrimaryPalette.primary30,
    onPrimaryContainer = PrimaryPalette.primary90,
    inversePrimary = PrimaryPalette.primary40,

    secondary = SecondaryPalette.secondary80,
    onSecondary = SecondaryPalette.secondary20,
    secondaryContainer = SecondaryPalette.secondary30,
    onSecondaryContainer = SecondaryPalette.secondary90,

    tertiary = TertiaryPalette.tertiary80,
    onTertiary = TertiaryPalette.tertiary20,
    tertiaryContainer = TertiaryPalette.tertiary30,
    onTertiaryContainer = TertiaryPalette.tertiary90,

    error = StatusPalette.error80,
    onError = StatusPalette.error20,
    errorContainer = StatusPalette.error30,
    onErrorContainer = StatusPalette.error90,

    background = NeutralPalette.neutral6,
    onBackground = NeutralPalette.neutral90,

    surface = NeutralPalette.neutral6,
    onSurface = NeutralPalette.neutral90,
    surfaceVariant = NeutralVariantPalette.nv30,
    onSurfaceVariant = NeutralVariantPalette.nv80,

    outline = NeutralVariantPalette.nv60,
    outlineVariant = NeutralVariantPalette.nv30,
    scrim = NeutralPalette.neutral0.copy(alpha = 0.60f),

    inverseSurface = NeutralPalette.neutral90,
    inverseOnSurface = NeutralPalette.neutral20,

    surfaceDim = NeutralPalette.neutral6,
    surfaceBright = NeutralPalette.neutral24,
    surfaceContainerLowest = NeutralPalette.neutral4,
    surfaceContainerLow = NeutralPalette.neutral10,
    surfaceContainer = NeutralPalette.neutral12,
    surfaceContainerHigh = NeutralPalette.neutral17,
    surfaceContainerHighest = NeutralPalette.neutral22,
    surfaceTint = PrimaryPalette.primary80,
)
