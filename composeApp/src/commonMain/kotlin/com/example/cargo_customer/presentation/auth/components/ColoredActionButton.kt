package com.example.cargo_customer.presentation.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cargo_customer.presentation.theme.AppTheme

@Composable
fun ColoredActionButton(
    modifier: Modifier = Modifier,
    onClick:()->Unit){
    Button(
        modifier=modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(size = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.surface
        ),
    ){
        Text(
            modifier = modifier.padding(12.dp),
            text = "Create An Account",
            style = AppTheme.typography.bodyMd)
    }
}