package com.example.cargo_customer.presentation.component
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
    text: String,
    onClick:()->Unit){
    Button(
        modifier=modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(size = AppTheme.dimens.gutter),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.surface
        ),
    ){
        Text(
            modifier = modifier.padding(12.dp),
            text = text,
            style = AppTheme.typography.bodyMd)
    }
}