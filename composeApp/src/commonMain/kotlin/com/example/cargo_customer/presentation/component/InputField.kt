package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.ic_visibility
import cargo_customer.composeapp.generated.resources.ic_visibility_off
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
    isPasswordField: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibilityChange: (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    focusRequester: FocusRequester = remember { FocusRequester() },
    onNext: (() -> Unit)? = null,
    onDone: (() -> Unit)? = null,
    errorMessage: String? = null,
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
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
            visualTransformation =
                if (isPasswordField && !isPasswordVisible)
                    PasswordVisualTransformation()
                else
                    VisualTransformation.None,
            placeholder = {
                Text(
                    text = placeholder,
                    style = CargoTheme.typography.bodyMedium,
                    color = CargoTheme.colorScheme.onSurfaceVariant
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = when {
                    onNext != null -> ImeAction.Next
                    onDone != null -> ImeAction.Done
                    else -> ImeAction.Default
                }
            ),
            keyboardActions = KeyboardActions(
                onNext = { onNext?.invoke() },
                onDone = { onDone?.invoke() }
            ),
            leadingIcon = leadingIconRes?.let { icon ->
                {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        tint = if (errorMessage != null)
                            CargoTheme.colorScheme.error
                        else
                            CargoTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(CargoTheme.dimens.sizing.iconMd)
                    )
                }
            },
            trailingIcon = {
                if (isPasswordField) {
                    IconButton(
                        onClick = { onVisibilityChange?.invoke() }
                    ) {
                        Icon(
                            painter =
                                if (isPasswordVisible)
                                    painterResource(Res.drawable.ic_visibility)
                                else
                                    painterResource( Res.drawable.ic_visibility_off),
                            tint = CargoTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(CargoTheme.dimens.sizing.iconMd),
                            contentDescription = null
                        )
                    }
                }
            },
            isError = errorMessage != null,
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
                errorBorderColor = CargoTheme.colorScheme.error,
                errorContainerColor = CargoTheme.colorScheme.surface,
                errorTextColor = CargoTheme.colorScheme.onSurface,
                cursorColor = CargoTheme.colorScheme.primary
            )
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                style = CargoTheme.typography.labelMedium,
                color = CargoTheme.colorScheme.error,
                modifier = Modifier.padding(start = CargoTheme.dimens.spacing.xs)
            )
        }
    }
}