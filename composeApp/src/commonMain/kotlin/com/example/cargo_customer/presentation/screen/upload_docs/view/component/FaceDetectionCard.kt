package com.example.cargo_customer.presentation.screen.upload_docs.view.component

import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.face_illustration_description
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.ScanFace
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.stringResource

private const val AspectRatioFaceCard = 0.9f
private const val InitialPulseAlpha = 0.3f
private const val TargetPulseAlpha = 0.8f
private const val PulseDurationMillis = 1200

@Composable
fun FaceDetectionCard(onClick: () -> Unit) {
    val dimens = CargoTheme.dimens
    val spacing = dimens.spacing
    val sizing = dimens.sizing
    val shapes = CargoTheme.shapes
    val colorScheme = CargoTheme.colorScheme

    val infiniteTransition = rememberInfiniteTransition(label = "scan_pulse")
    val overlayAlpha by infiniteTransition.animateFloat(
        initialValue = InitialPulseAlpha,
        targetValue = TargetPulseAlpha,
        animationSpec = infiniteRepeatable(
            animation = tween(PulseDurationMillis, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_alpha"
    )

    Box(
        modifier = Modifier.fillMaxWidth().aspectRatio(AspectRatioFaceCard).clip(shapes.extraLarge)
            .background(colorScheme.surface).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = Lucide.ScanFace,
            contentDescription = stringResource(Res.string.face_illustration_description),
            modifier = Modifier.size(spacing.colossal + spacing.colossal + sizing.iconXs),
            colorFilter = ColorFilter.tint(CargoTheme.extendedColors.shimmer.copy(alpha = overlayAlpha))
        )
    }
}