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
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.create_an_account
import com.example.cargo_customer.presentation.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun ColoredActionButton(
    modifier: Modifier = Modifier,
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
            text = stringResource(Res.string.create_an_account),
            style = AppTheme.typography.bodyMd)
    }
}