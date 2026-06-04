package com.example.cargo_customer.presentation.screen.upload_docs.view.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import com.example.cargo_customer.presentation.theme.CargoTheme

/**
 * A custom Jetpack Compose UI component that renders a horizontally connected,
 * fluid animated stepper indicator. The progress line smoothly interpolates
 * between segments using spring physics, while the segment colors crossfade
 * based on their completion status.
 *
 * @param stepCount The total number of steps in the stepper sequence.
 * @param currentIndex The index of the currently active step (0-based).
 * @param modifier The [Modifier] to be applied to the stepper container.
 * @param spacingWidth The width of the gap between individual step segments.
 * @param inactiveColor The color used for the skeleton track of steps that haven't been reached yet.
 * @param activeColor The color used for the step that is currently being progressed.
 * @param doneColor The color used for steps that have already been fully completed.
 */
@Composable
fun ConnectedFluidStepper(
    stepCount: Int,
    currentIndex: Int,
    modifier: Modifier = Modifier,
    spacingWidth: Dp = CargoTheme.dimens.spacing.md,
    inactiveColor: Color = CargoTheme.extendedColors.shimmer,
    activeColor: Color = CargoTheme.colorScheme.primary,
    doneColor: Color = CargoTheme.colorScheme.primary,
) {
    val segmentColors = List(stepCount) { i ->
        val targetColor = if (i < currentIndex) doneColor else activeColor
        animateColorAsState(
            targetValue = targetColor,
            animationSpec = tween(durationMillis = 400),
            label = "segment_color_$i"
        )
    }

    BoxWithConstraints(
        modifier = modifier.semantics {
            progressBarRangeInfo = ProgressBarRangeInfo(
                current = currentIndex.toFloat(),
                range = 0f..stepCount.toFloat()
            )
        }
    ) {
        val widthPx = constraints.maxWidth.toFloat()
        val spacingPx = with(LocalDensity.current) { spacingWidth.toPx() }
        val spacingCount = (stepCount - 1).coerceAtLeast(0)
        val totalSpacingPx = spacingPx * spacingCount
        val stepWidth = (widthPx - totalSpacingPx) / stepCount

        val targetX = (currentIndex * (stepWidth + spacingPx) + stepWidth).coerceAtMost(widthPx)

        val animatedX by animateFloatAsState(
            targetValue = targetX,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            ),
            label = "stepper_physical_progress"
        )

        Canvas(modifier = Modifier.fillMaxSize()) {
            val yCenter = size.height / 2
            val headX = animatedX

            for (i in 0 until stepCount) {
                val startX = i * (stepWidth + spacingPx)
                val endX = startX + stepWidth

                drawLine(
                    color = inactiveColor,
                    start = Offset(startX, yCenter),
                    end = Offset(endX, yCenter),
                    strokeWidth = size.height,
                    cap = StrokeCap.Round
                )

                if (headX > startX) {
                    val currentFillEndX = headX.coerceAtMost(endX)

                    val fillColor = segmentColors[i].value

                    drawLine(
                        color = fillColor,
                        start = Offset(startX, yCenter),
                        end = Offset(currentFillEndX, yCenter),
                        strokeWidth = size.height,
                        cap = StrokeCap.Round
                    )
                }
            }
        }
    }
}