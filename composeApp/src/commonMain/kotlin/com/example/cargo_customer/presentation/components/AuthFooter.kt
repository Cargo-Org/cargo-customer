package com.example.cargo_customer.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cargo_customer.presentation.theme.AppTheme

@Composable
fun AuthFooterText(
    modifier: Modifier = Modifier,
    promptText: String,
    actionText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = promptText,
            style = AppTheme.typography.bodyMd,
            color = AppTheme.colors.onSurfaceVariant
        )
        TextButton(
            onClick = onClick,
        ) {
            Text(
                text = actionText,
                style = AppTheme.typography.labelMd,
                color = AppTheme.colors.primary
            )
        }
    }
}