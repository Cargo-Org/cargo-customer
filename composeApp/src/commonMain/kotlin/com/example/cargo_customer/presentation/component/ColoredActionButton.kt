package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.example.cargo_customer.presentation.theme.CargoTheme

@Composable
fun ColoredActionButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    shape: Shape = CargoTheme.shapes.large,
    textModifier : Modifier = Modifier.padding(vertical = CargoTheme.dimens.spacing.md),
    enabled: Boolean = true
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = CargoTheme.colorScheme.primary,
            contentColor = CargoTheme.colorScheme.onPrimary
        ),
        enabled = enabled
    ) {
        Text(
            modifier = textModifier,
            text = text,
            style = CargoTheme.typography.bodyMedium
        )
    }
}
@Preview
@Composable
fun ColoredActionButtonPreview() {
    ColoredActionButton(
        text = "Action Button",
        onClick = {}
    )
}