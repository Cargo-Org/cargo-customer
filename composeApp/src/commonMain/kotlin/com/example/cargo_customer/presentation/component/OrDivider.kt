package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cargo_customer.presentation.theme.CargoTheme

@Composable
fun OrDivider(
    modifier: Modifier = Modifier,
    centerText: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = CargoTheme.colorScheme.outlineVariant
        )
        Text(
            modifier = Modifier.padding(horizontal = CargoTheme.dimens.spacing.sm),
            text = centerText,
            style = CargoTheme.typography.labelSmall,
            color = CargoTheme.colorScheme.outline
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = CargoTheme.colorScheme.outlineVariant
        )
    }
}