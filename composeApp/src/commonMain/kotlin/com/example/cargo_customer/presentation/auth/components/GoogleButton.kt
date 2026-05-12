package com.example.cargo_customer.presentation.auth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.cargo_customer.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GoogleButton (modifier: Modifier , onClick:()->Unit) {
    OutlinedButton(
        modifier=modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(size = AppTheme.dimens.gutter),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.surface,
            contentColor = AppTheme.colors.onSurface
        ),
        border = BorderStroke(1.dp, AppTheme.colors.outlineVariant)
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                contentDescription = "",
                painter = painterResource(Res.drawable.ic_google),
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Text(
                modifier = modifier.padding(12.dp),
                text = stringResource(Res.string.google_login),
                style = AppTheme.typography.bodyMd)
        }
    }
}