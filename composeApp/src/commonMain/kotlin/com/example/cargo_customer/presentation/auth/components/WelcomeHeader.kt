package com.example.cargo_customer.presentation.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cargo_customer.presentation.theme.AppTheme

@Composable
fun WelcomeHeader(
    modifier: Modifier = Modifier,
    headerTitle: String,
    subTitle: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.base),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = headerTitle,
            style = AppTheme.typography.headlineXl,
            color = AppTheme.colors.primary,
            textAlign = TextAlign.Center
        )
        Text(
            text = subTitle,
            style = AppTheme.typography.bodyStandard,
            color = AppTheme.colors.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(horizontal = AppTheme.dimens.md),
        )
    }
}