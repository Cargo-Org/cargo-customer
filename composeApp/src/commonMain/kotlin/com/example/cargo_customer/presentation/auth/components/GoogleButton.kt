package com.example.cargo_customer.presentation.auth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cargo_customer.presentation.theme.AppTheme

@Composable
fun GoogleButton (modifier: Modifier , onClick:()->Unit) {
    OutlinedButton(
        modifier=modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(size = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.surface,
            contentColor = AppTheme.colors.onSurface
        ),
        border = BorderStroke(1.dp, AppTheme.colors.outlineVariant)
    ){
        Text(
            modifier = modifier.padding(12.dp),
            text = "Google",
            style = AppTheme.typography.bodyMd)
    }
}