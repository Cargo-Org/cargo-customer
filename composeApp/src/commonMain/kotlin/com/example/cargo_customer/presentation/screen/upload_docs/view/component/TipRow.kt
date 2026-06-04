package com.example.cargo_customer.presentation.screen.upload_docs.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cargo_customer.presentation.theme.CargoTheme

@Composable
fun TipRow(
    text: String,
    textColor: Color = CargoTheme.colorScheme.onBackground,
    icon: ImageVector,
    iconColor: Color = CargoTheme.colorScheme.primary,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(CargoTheme.dimens.sizing.iconXl)
                .background(color = iconColor.copy(alpha = 0.2F), shape = CargoTheme.shapes.small)
                .padding(CargoTheme.dimens.spacing.sm),
            contentAlignment = Alignment.Center)
        {
            Image(icon, contentDescription = null, colorFilter = ColorFilter.tint(iconColor))
        }

        Spacer(modifier = Modifier.width(CargoTheme.dimens.spacing.lg))

        Text(
            text = text, color = textColor, style = CargoTheme.typography.labelMedium
        )
    }
}