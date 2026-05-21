package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.ic_email
import com.example.cargo_customer.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun HeaderWithIcon(
    painter: Painter,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    iconBackgroundColor: Color = AppTheme.colors.primary,
    iconTintColor: Color = AppTheme.colors.surface
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppTheme.dimens.md),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = iconBackgroundColor,
                    shape = AppTheme.shapes.component5()
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painter,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = title,
            style = AppTheme.typography.headlineXl,
            color = AppTheme.colors.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = description,
            style = AppTheme.typography.bodyMd,
            color = AppTheme.colors.outline,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderPreview() {
    HeaderWithIcon(
        painter = painterResource(Res.drawable.ic_email),
        title = "Verify Identity",
        description = "We've sent a 6-digit verification code\n" +
                "to your email address."
    )
}