package com.example.cargo_customer.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cargo_customer.presentation.theme.AppTheme

@Composable
fun OrDivider(
    modifier: Modifier,
    centerText: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = AppTheme.dimens.xs / 4,
            color = AppTheme.colors.outlineVariant
        )
        Text(
            modifier = Modifier.padding(horizontal = AppTheme.dimens.stackSm),
            text = centerText,
            style = AppTheme.typography.labelSm,
            color = AppTheme.colors.outline
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = AppTheme.dimens.xs / 4,
            color = AppTheme.colors.outlineVariant
        )
    }
}