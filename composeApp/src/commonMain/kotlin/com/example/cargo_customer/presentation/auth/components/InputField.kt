package com.example.cargo_customer.presentation.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cargo_customer.presentation.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value:String,
    onValueChanged :(String)->Unit,
    label:String,
    placeholder:String,
    leadingIconRes: DrawableResource? = null,){
    Column (
        modifier = modifier.fillMaxWidth().padding(vertical = 6.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = label,
            style = AppTheme.typography.labelMd,
            color = AppTheme.colors.onSurfaceVariant)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = AppTheme.typography.bodyMd,
                    color = AppTheme.colors.onSurfaceVariant
                )
            },
            leadingIcon = leadingIconRes?.let { icon ->
                {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        tint = AppTheme.colors.onSurfaceVariant,
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            shape = AppTheme.shapes.large,
            textStyle = AppTheme.typography.bodyMd,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = AppTheme.colors.surface,
                unfocusedContainerColor = AppTheme.colors.surface,
                focusedBorderColor = AppTheme.colors.outlineVariant,
                unfocusedBorderColor = AppTheme.colors.outlineVariant,
                focusedTextColor = AppTheme.colors.onSurface,
                unfocusedTextColor = AppTheme.colors.onSurface,
                focusedPlaceholderColor = AppTheme.colors.onSurfaceVariant,
                unfocusedPlaceholderColor = AppTheme.colors.onSurfaceVariant,
                cursorColor = AppTheme.colors.primary
            )
        )
    }
}