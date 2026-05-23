package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cargo_customer.presentation.theme.CargoTheme

@Composable
fun OTPInputRow(
    codeLength: Int,
    code: String,
    onBoxClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(CargoTheme.dimens.spacing.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(codeLength) { index ->
            val char = code.getOrNull(index)?.toString() ?: ""
            val isActive = index == code.length
            val isFilled = index < code.length

            Box(
                modifier = Modifier
                    .size(CargoTheme.dimens.sizing.minTouchTarget)
                    .background(
                        color = if (isFilled) CargoTheme.colorScheme.surfaceContainerHigh else CargoTheme.colorScheme.surface,
                        shape = CargoTheme.shapes.medium
                    )
                    .border(
                        width = if (isActive) 2.dp else 1.dp,
                        color = when {
                            isActive -> CargoTheme.colorScheme.primary
                            isFilled -> CargoTheme.colorScheme.primary.copy(alpha = 0.4f)
                            else -> CargoTheme.colorScheme.outline.copy(alpha = 0.4f)
                        },
                        shape = CargoTheme.shapes.medium
                    )
                    .clickable { onBoxClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = char,
                    style = CargoTheme.typography.titleMedium,
                    color = CargoTheme.colorScheme.onSurface
                )
            }
        }
    }
}