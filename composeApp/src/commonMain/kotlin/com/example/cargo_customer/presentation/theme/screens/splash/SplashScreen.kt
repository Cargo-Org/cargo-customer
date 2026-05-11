package com.example.cargo_customer.presentation.theme.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.cargo
import cargo_customer.composeapp.generated.resources.ic_app_icon_light
import com.example.cargo_customer.presentation.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun SplashScreen() {
    SplashScreenContent()
}

@Composable
private fun SplashScreenContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().background(AppTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(80.dp).clip(shape = RoundedCornerShape(AppTheme.dimens.md))
                .background(AppTheme.colors.primary).border(
                    width = 1.dp, color = AppTheme.colors.background
                ), contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_app_icon_light),
                contentDescription = null,
                tint = AppTheme.colors.background
            )
        }

        Spacer(modifier = Modifier.height(AppTheme.dimens.md))

        Text(
            text = stringResource( Res.string.cargo), style = AppTheme.typography.displayHero, color = AppTheme.colors.primary
        )
    }
}

@Composable
@Preview
private fun Preview() {
    SplashScreenContent()
}