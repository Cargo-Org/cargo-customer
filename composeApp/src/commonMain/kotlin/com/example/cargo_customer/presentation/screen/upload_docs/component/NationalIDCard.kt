package com.example.cargo_customer.presentation.screen.upload_docs.component

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.back_face_capture_instruction
import cargo_customer.composeapp.generated.resources.back_face_image_description
import cargo_customer.composeapp.generated.resources.front_face_capture_instruction
import cargo_customer.composeapp.generated.resources.front_face_image_description
import com.composables.icons.lucide.CreditCard
import com.composables.icons.lucide.IdCard
import com.composables.icons.lucide.Lucide
import com.example.cargo_customer.presentation.component.ClickableOverlay
import com.example.cargo_customer.presentation.theme.CargoTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

const val CardAspectRatio = 16f / 9f

typealias OnClickCallback = () -> Unit
typealias OnFaceChange = (newIndex: Int) -> Unit

@Composable
fun NationalIDCard(
    currentIndex: Int,
    swipeToFlipEnabled: Boolean = true,
    frontImage: Painter? = null,
    backImage: Painter? = null,
    onFrontClick: OnClickCallback,
    onBackClick: OnClickCallback,
    onIndexChanged: OnFaceChange,
) {
    val viewStateGroup = when (currentIndex) {
        0, 1 -> 0
        2, 3 -> 2
        else -> currentIndex
    }

    Crossfade(
        targetState = viewStateGroup,
        animationSpec = tween(durationMillis = 400),
        label = "card_crossfade"
    ) { group ->
        when (group) {
            0 -> InteractiveFlipCard(
                currentIndex = currentIndex,
                swipeToFlipEnabled = swipeToFlipEnabled,
                frontImage = frontImage,
                backImage = backImage,
                onFrontClick = onFrontClick,
                onBackClick = onBackClick,
                onIndexChanged = onIndexChanged
            )

            2 -> ProcessingFace(isSuccess = currentIndex == 3)
        }
    }
}

@Composable
private fun InteractiveFlipCard(
    currentIndex: Int,
    swipeToFlipEnabled: Boolean,
    frontImage: Painter?,
    backImage: Painter?,
    onFrontClick: OnClickCallback,
    onBackClick: OnClickCallback,
    onIndexChanged: OnFaceChange,
) {
    val coroutineScope = rememberCoroutineScope()
    val rotation = remember { Animatable(if (currentIndex == 1) 180f else 0f) }

    val flipAnimationSpec = remember {
        spring<Float>(
            dampingRatio = 0.65f, stiffness = Spring.StiffnessLow
        )
    }

    LaunchedEffect(currentIndex) {
        val isCurrentlyFront = (abs(rotation.targetValue / 180f).roundToInt() % 2) == 0
        val targetIsFront = currentIndex == 0

        if (isCurrentlyFront != targetIsFront) {
            rotation.animateTo(rotation.targetValue + 180f, flipAnimationSpec)
        }
    }

    Box(
        modifier = Modifier.fillMaxWidth().aspectRatio(CardAspectRatio)
        .pointerInput(swipeToFlipEnabled) {
            if (!swipeToFlipEnabled) return@pointerInput

            var dragAccumulator = 0f
            var hasTriggered = false

            detectVerticalDragGestures(onDragStart = {
                dragAccumulator = 0f
                hasTriggered = false
            }, onVerticalDrag = { change, dragAmount ->
                change.consume()

                if (hasTriggered) return@detectVerticalDragGestures

                dragAccumulator += dragAmount
                val threshold = 40f

                if (dragAccumulator < -threshold) {
                    hasTriggered = true
                    coroutineScope.launch {
                        val nextTarget = rotation.targetValue + 180f
                        val isFront = (abs(nextTarget / 180f).roundToInt() % 2) == 0
                        onIndexChanged(if (isFront) 0 else 1)
                        rotation.animateTo(nextTarget, flipAnimationSpec)
                    }
                }
                if (dragAccumulator > threshold) {
                    hasTriggered = true
                    coroutineScope.launch {
                        val nextTarget = rotation.targetValue - 180f
                        val isFront = (abs(nextTarget / 180f).roundToInt() % 2) == 0
                        onIndexChanged(if (isFront) 0 else 1)
                        rotation.animateTo(nextTarget, flipAnimationSpec)
                    }
                }
            })
        }.graphicsLayer {
            rotationX = rotation.value
            cameraDistance = 12f * density
        }) {
        val normalizedRotation = abs(rotation.value) % 360f
        val isFrontVisible = normalizedRotation <= 90f || normalizedRotation >= 270f

        if (isFrontVisible) {
            IdCardFrontFace(
                image = frontImage, onClick = onFrontClick
            )
            return@Box
        }

        Box(modifier = Modifier.graphicsLayer { rotationX = 180f }) {
            IdCardBackFace(
                image = backImage, onClick = onBackClick
            )
        }
    }
}

@Composable
private fun IdCardFrontFace(
    image: Painter?, onClick: OnClickCallback
) {
    val dimens = CargoTheme.dimens
    val spacing = dimens.spacing
    val sizing = dimens.sizing
    val shapes = CargoTheme.shapes

    val cardBackground = CargoTheme.extendedColors.cardBackground
    val elementBackground = CargoTheme.extendedColors.shimmer

    Box(
        modifier = Modifier.fillMaxSize().clip(shapes.large).background(cardBackground)
            .clickable { onClick() }) {
        if (image != null) {
            Image(
                painter = image,
                contentDescription = stringResource(Res.string.front_face_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            return@Box
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(dimens.cardPaddingLarge),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier.width(spacing.colossal).height(sizing.avatarXl)
                        .clip(shapes.medium).background(elementBackground)
                )

                Box(
                    modifier = Modifier.padding(top = spacing.md).width(spacing.colossal * 2f)
                        .height(spacing.xxxl).clip(shapes.medium).background(elementBackground)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom
            ) {
                Box(
                    modifier = Modifier.width(sizing.bottomBarHeight * 1.5f).height(spacing.xxxl)
                        .clip(shapes.medium).background(elementBackground)
                )

                Spacer(modifier = Modifier.width(spacing.lg))

                Box(
                    modifier = Modifier.weight(1f).height(spacing.xxxl).clip(shapes.medium)
                        .background(elementBackground)
                )
            }
        }
        ClickableOverlay(
            icon = Lucide.IdCard, text = stringResource(Res.string.front_face_capture_instruction)
        ) {}
    }
}

@Composable
private fun IdCardBackFace(
    image: Painter?, onClick: OnClickCallback
) {
    val dimens = CargoTheme.dimens
    val spacing = dimens.spacing
    val sizing = dimens.sizing
    val shapes = CargoTheme.shapes

    val cardBackground = CargoTheme.extendedColors.cardBackground
    val elementBackground = CargoTheme.extendedColors.shimmer

    Box(
        modifier = Modifier.fillMaxSize().clip(shapes.large).background(cardBackground)
            .clickable { onClick() }) {
        if (image != null) {
            Image(
                painter = image,
                contentDescription = stringResource(Res.string.back_face_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            return@Box
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(dimens.cardPaddingLarge),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(spacing.md),
                    modifier = Modifier.padding(top = spacing.sm)
                ) {
                    Box(
                        modifier = Modifier.width(sizing.bottomBarHeight * 1.25f).height(spacing.lg)
                            .clip(shapes.small).background(elementBackground)
                    )
                    Box(
                        modifier = Modifier.width(sizing.bottomBarHeight * 2f).height(spacing.xl)
                            .clip(shapes.small).background(elementBackground)
                    )
                }

                Spacer(modifier = Modifier.width(spacing.lg))

                Box(
                    modifier = Modifier.size(spacing.colossal).clip(shapes.medium)
                        .background(elementBackground)
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth().height(spacing.colossal).clip(shapes.medium)
                    .background(elementBackground)
            )
        }
        ClickableOverlay(
            icon = Lucide.CreditCard,
            text = stringResource(Res.string.back_face_capture_instruction),
        ) {}


    }
}

@Composable
private fun ProcessingFace(isSuccess: Boolean) {
    val dimens = CargoTheme.dimens
    val spacing = dimens.spacing
    val shapes = CargoTheme.shapes

    val timeAnim = remember { Animatable(0f) }

    LaunchedEffect(isSuccess) {
        if (!isSuccess) {
            while (true) {
                val remaining = (2 * PI).toFloat() - timeAnim.value
                val duration = ((remaining / (2 * PI).toFloat()) * 6000).toInt()

                if (duration > 0) {
                    timeAnim.animateTo(
                        targetValue = (2 * PI).toFloat(),
                        animationSpec = tween(duration, easing = LinearEasing)
                    )
                }
                timeAnim.snapTo(0f)
            }
        }
    }

    val time = timeAnim.value

    val cardBackground = CargoTheme.extendedColors.cardBackground
    val primaryOrb = CargoTheme.colorScheme.primary
    val secondaryOrb = CargoTheme.colorScheme.secondary
    val tertiaryOrb = CargoTheme.colorScheme.tertiary
    val highlightOrb = CargoTheme.colorScheme.onSurface

    Box(
        modifier = Modifier.fillMaxWidth().aspectRatio(CardAspectRatio).clip(shapes.large)
            .background(cardBackground), contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize().blur(radius = spacing.massive)
        ) {
            val width = size.width
            val height = size.height

            val primaryCenter = Offset(
                x = width * 0.5f + (sin(time) * width * 0.4f),
                y = height * 0.9f + (cos(time) * height * 0.2f)
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(primaryOrb.copy(alpha = 0.6f), Color.Transparent),
                    center = primaryCenter,
                    radius = width * 0.7f
                ), center = primaryCenter, radius = width * 0.7f
            )

            val secondaryCenter = Offset(
                x = width * 0.5f - (cos(time * 2f) * width * 0.3f),
                y = height * 0.8f + (sin(time) * height * 0.3f)
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(secondaryOrb.copy(alpha = 0.5f), Color.Transparent),
                    center = secondaryCenter,
                    radius = width * 0.8f
                ), center = secondaryCenter, radius = width * 0.8f
            )

            val tertiaryCenter = Offset(
                x = width * 0.5f + (sin(time) * width * 0.2f), y = height * 1.1f
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(tertiaryOrb.copy(alpha = 0.8f), Color.Transparent),
                    center = tertiaryCenter,
                    radius = width * 0.9f
                ), center = tertiaryCenter, radius = width * 0.9f
            )

            val highlightCenter = Offset(
                x = width * 0.5f + (cos(time) * width * 0.1f),
                y = height * 0.85f + (sin(time * 2f) * height * 0.05f)
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(highlightOrb.copy(alpha = 0.4f), Color.Transparent),
                    center = highlightCenter,
                    radius = width * 0.4f
                ), center = highlightCenter, radius = width * 0.4f
            )
        }

        Crossfade(
            targetState = isSuccess, animationSpec = tween(400), label = "success_overlay_crossfade"
        ) { success ->
            if (success) {
                SuccessOverlay()
                return@Crossfade
            }
        }
    }
}
