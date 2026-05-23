package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.cargo_customer.presentation.theme.CargoTheme

@Composable
fun WelcomeHeader(
    modifier: Modifier = Modifier,
    headerTitle: String,
    subTitle: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(CargoTheme.dimens.spacing.sm),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = headerTitle,
            style = CargoTheme.typography.headlineLarge,
            color = CargoTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        Text(
            text = subTitle,
            style = CargoTheme.typography.bodyLarge,
            color = CargoTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = CargoTheme.dimens.spacing.md),
        )
    }
}