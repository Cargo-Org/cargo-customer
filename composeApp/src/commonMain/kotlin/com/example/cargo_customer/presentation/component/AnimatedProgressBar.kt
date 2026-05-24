package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cargo_customer.presentation.theme.CargoTheme


@Composable
fun AnimatedProgressBar(
    modifier: Modifier = Modifier,
    barColor: Color = CargoTheme.colorScheme.onBackground.copy(alpha = 0.2f),
    progressColor: Color = CargoTheme.colorScheme.primary,
    progress: Float = 0f
) {
    Box(
        modifier = modifier.clip(RoundedCornerShape(CargoTheme.dimens.spacing.xxl))
            .background(barColor)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(progress).height(CargoTheme.dimens.spacing.xs + 2.dp)
                .clip(RoundedCornerShape(CargoTheme.dimens.spacing.xxl)).background(progressColor)
        )
    }
}