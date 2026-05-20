package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.google_login
import cargo_customer.composeapp.generated.resources.ic_google
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GoogleButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    OutlinedButton(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = CargoTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            containerColor = CargoTheme.colorScheme.surface,
            contentColor = CargoTheme.colorScheme.onSurface
        ),
        border = BorderStroke(1.dp, CargoTheme.colorScheme.outlineVariant)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                contentDescription = null,
                painter = painterResource(Res.drawable.ic_google),
                modifier = Modifier.size(CargoTheme.dimens.sizing.iconMd),
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier.padding(vertical = CargoTheme.dimens.spacing.md),
                text = stringResource(Res.string.google_login),
                style = CargoTheme.typography.bodyMedium
            )
        }
    }
}