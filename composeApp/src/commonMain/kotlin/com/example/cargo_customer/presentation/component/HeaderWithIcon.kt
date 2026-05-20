package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.ic_email
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun HeaderWithIcon(
    painter: Painter,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    iconBackgroundColor: Color = CargoTheme.colorScheme.primary,
    iconTintColor: Color = CargoTheme.colorScheme.onPrimary
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(CargoTheme.dimens.spacing.md),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(CargoTheme.dimens.sizing.avatarXl)
                .background(
                    color = iconBackgroundColor,
                    shape = CargoTheme.shapes.extraLarge
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painter,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier.size(CargoTheme.dimens.sizing.iconXl)
            )
        }

        Spacer(modifier = Modifier.height(CargoTheme.dimens.spacing.xxxl))

        Text(
            text = title,
            style = CargoTheme.typography.headlineLarge,
            color = CargoTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(CargoTheme.dimens.spacing.md))

        Text(
            text = description,
            style = CargoTheme.typography.bodyMedium,
            color = CargoTheme.colorScheme.outline,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderPreview() {
    HeaderWithIcon(
        painter = painterResource(Res.drawable.ic_email),
        title = "Verify Identity",
        description = "We've sent a 6-digit verification code\n" +
                "to your email address."
    )
}