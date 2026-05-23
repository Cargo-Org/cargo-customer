package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIconRes: DrawableResource? = null,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = CargoTheme.dimens.spacing.xs),
        verticalArrangement = Arrangement.spacedBy(CargoTheme.dimens.spacing.sm)
    ) {
        Text(
            text = label,
            style = CargoTheme.typography.labelMedium,
            color = CargoTheme.colorScheme.onSurfaceVariant
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = CargoTheme.typography.bodyMedium,
                    color = CargoTheme.colorScheme.onSurfaceVariant
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            leadingIcon = leadingIconRes?.let { icon ->
                {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        tint = CargoTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(CargoTheme.dimens.sizing.iconMd)
                    )
                }
            },
            shape = CargoTheme.shapes.small,
            textStyle = CargoTheme.typography.bodyMedium,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = CargoTheme.colorScheme.surface,
                unfocusedContainerColor = CargoTheme.colorScheme.surface,
                focusedBorderColor = CargoTheme.colorScheme.primary,
                unfocusedBorderColor = CargoTheme.colorScheme.outlineVariant,
                focusedTextColor = CargoTheme.colorScheme.onSurface,
                unfocusedTextColor = CargoTheme.colorScheme.onSurface,
                focusedPlaceholderColor = CargoTheme.colorScheme.onSurfaceVariant,
                unfocusedPlaceholderColor = CargoTheme.colorScheme.onSurfaceVariant,
                cursorColor = CargoTheme.colorScheme.primary
            )
        )
    }
}